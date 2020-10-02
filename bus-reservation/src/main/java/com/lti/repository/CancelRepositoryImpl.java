package com.lti.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lti.entity.Booking;
import com.lti.exception.BusServiceException;

@Repository
public class CancelRepositoryImpl implements CancelRepository {

	@Autowired
	public EntityManager entityManager;
	
	@Override
	public Booking updateStatus(int id,String bookingStatus) {
		int count= entityManager.createQuery("update Booking b set b.status =:bs where b.customer.id=:c and b.status =:booked")
				.setParameter("booked", "booked")
				.setParameter("bs", bookingStatus)
				.setParameter("c", id)
				.executeUpdate();
		if(count==1)
			return (Booking)entityManager.createQuery("select b from Booking b where b.customer.id= :c")
			.setParameter("c", id)
			.getSingleResult();
		else
			throw new BusServiceException("You don't have any bookings for now");
	}
}
