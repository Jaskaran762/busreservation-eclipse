package com.lti.repository;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
		double updatedWallet;
		if(customerId!=0) {
			double walletBalance=walletService.getBalance(customerId);
			updatedWallet=walletBalance-amount; //only if sufficient wallet amount
			walletService.setBalance(customerId, updatedWallet);

			}
		else
			updatedWallet=99;
			
		
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
	
}
