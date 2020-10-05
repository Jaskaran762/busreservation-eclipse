package com.lti.repository;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lti.entity.Booking;
import com.lti.entity.Bus;
import com.lti.entity.Payment;
import com.lti.exception.BusServiceException;

@Repository
public class PaymentRepositoryImpl extends GenericRepositoryImpl implements PaymentRepository {	
	
	@Override
	public void add(int bus_id) {
		
		//get the bus record having bus_id of the one that's selected and calculate its amount according to its stops
		Bus bus=(Bus)entityManager.createQuery("select b from Bus b inner join Booking book where b.id=:bus_id")
				.setParameter("bus_Id", bus_id)
				.getSingleResult();
		double amount=bus.getAmount(); 
				
		try {
			Payment payment=new Payment();
			payment.setAmount(amount);
			payment.setPaymentDateTime(LocalDateTime.now());
			payment.setPaymentType(payment.getPaymentType());
			payment.setStatus("completed");
		
			save(payment);
		}
		catch(BusServiceException e) {
			System.out.println("Payment is pending...");
		}
	}
}
