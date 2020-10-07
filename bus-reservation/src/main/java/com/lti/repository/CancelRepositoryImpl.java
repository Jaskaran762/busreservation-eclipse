package com.lti.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.Booking;
import com.lti.entity.Customer;
import com.lti.exception.BusServiceException;

@Repository
public class CancelRepositoryImpl extends GenericRepositoryImpl implements CancelRepository {

	
	@Override
	@Transactional
	public boolean updateStatus(int id) {  	//update Booking set status="cancelled" where customer_id=1 and status="booked";
		String bookingStatus="Cancelled";
		int count=0;
		Booking booking=fetchById(Booking.class,id);
		System.out.println(booking.getStatus());
		if(booking.getStatus().equals("booked")) {
				count= entityManager.createQuery("update Booking b set b.status=:setStatus where b.id=:book_id")				
				.setParameter("setStatus", bookingStatus)
				.setParameter("book_id", id)
				.executeUpdate();
		}
		else {
			throw new BusServiceException("Already cancelled");
		}
			
		System.out.println(count);
		if(count>=1)
			return true;
		else
			return false;
	}
	
	public double getAmount(int bookingId) {  //gets booking amount
		double amt=(Double)entityManager.createQuery("select p.amount from Payment p inner join p.booking b where b.id=:bookingId")
				.setParameter("bookingId", bookingId)
				.getSingleResult();
		return amt;
	}
	
	public boolean addWallet(double amt,int id) {   //refund to wallet
		Customer customer=fetchById(Customer.class,id);
		double updatedAmount=customer.getWallet() +amt;
		int row=entityManager.createQuery("update Customer c set wallet=:amount where c.id=:id")
		.setParameter("amount", updatedAmount)
		.setParameter("id", id)
		.executeUpdate();
		return row==1?true:false;
	}
}