package com.lti.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.Booking;
import com.lti.exception.BusServiceException;

@Repository
public class CancelRepositoryImpl implements CancelRepository {

	@Autowired
	public EntityManager entityManager;
	//update Booking set status="cancelled" where customer_id=1 and status="booked";
	@Override
	@Transactional
	public Booking updateStatus(int id,String bookingStatus) {
		String booked="Booked";
		System.out.println(id);
		int count= entityManager.createQuery("update Booking b set b.status=:bs where b.customer.id=:c and b.status=:booked")
				.setParameter("booked", booked)
				.setParameter("bs", bookingStatus)
				.setParameter("c", id)
				.executeUpdate();
		System.out.println(count);
		if(count>=1)
			return (Booking)entityManager.createQuery("select b from Booking b where b.customer.id= :c")
			.setParameter("c", id)
			.getSingleResult();
		else
			throw new BusServiceException("You don't have any bookings for now");
	}
}
