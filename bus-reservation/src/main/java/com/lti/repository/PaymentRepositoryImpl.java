package com.lti.repository;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.Booking;
import com.lti.entity.Bus;
import com.lti.entity.Payment;
import com.lti.exception.BusServiceException;
import com.lti.service.WalletService;

@Repository
public class PaymentRepositoryImpl extends GenericRepositoryImpl implements PaymentRepository {	
	
	@Autowired
	private WalletService walletService;
	
	@Override
	public Payment  add(int busId,int customerId) {
		Payment payment=null;
		//get the bus record having bus_id of the one that's selected and calculate its amount according to its stops
		Bus bus=(Bus)entityManager.createQuery("select b from Bus b where b.id=:bus_id")
				.setParameter("bus_id", busId)
				.getSingleResult();
		double amount=bus.getAmount(); 
		double updatedWallet=99;
		if(customerId!=0) {
			double walletBalance=walletService.getBalance(customerId);
			updatedWallet=walletBalance-amount; //only if sufficient wallet amount
			walletService.setBalance(customerId, updatedWallet);

			}
		else { //if customerId ==0 i.e not registered
			Booking b=new Booking();
			//b.setMobileNumber(mobileNo);
			b.setPayment(payment);//payment must b done after 
			b.setBus(bus);
			//b.setPassengers(passengers);
			//b.setSeatsBooked(seatsBooked);
			b.setTimeOfBooking(LocalTime.now());
			//b.setDateOfTravel(dateOfTravel);
			//b.setTravelRoute(travelRoute);
			b.setStatus("booked");
			save(b);
		}
		
		if(updatedWallet>0) {
			//System.out.println(walletBalance);
			//System.out.println(amount);
			
			payment=new Payment();
			payment.setAmount(amount);
			payment.setPaymentDateTime(LocalDateTime.now());
			payment.setPaymentType("wallet");
			payment.setStatus("completed");
		
			Payment payment1=save(payment);	
			int paymentId=payment1.getId();
			payment.setId(paymentId);
			
		}
		else {
			throw new BusServiceException("Payment is pending...");
		}
		
		return payment;
	}
	
	@Override
	@Transactional
	public void bookingStatus(int paymentId) {
		String booked="booked";
		entityManager.createQuery("update Booking b set b.status=:booked  where b.id in (select b1 from Booking b1 join b1.payment p where p.id=:paymentId)")
		.setParameter("paymentId", paymentId)
		.setParameter("booked", booked)
		.executeUpdate();
	}
	
	public Payment fetchByDateTimeandPanCard(LocalDateTime dateTime, String panCard) {
		
		return (Payment)entityManager.createQuery("select p from Payment p join p.booking b where p.paymentDateTime=:dateTime and b.panCard=:panCard")
				.setParameter("dateTime", dateTime)
				.setParameter("panCard", panCard)
				.getSingleResult();
	}
}
