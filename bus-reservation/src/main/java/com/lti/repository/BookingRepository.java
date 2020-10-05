package com.lti.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lti.entity.Booking;

@Repository
public class BookingRepository extends GenericRepositoryImpl {

	public List<Booking> fetchBookingsByCustomerId(int customerId){
		
		System.out.println(customerId);
		List<Booking> bookings = (List<Booking>)entityManager.createQuery("select b from Booking b where b.customer.id=:id")
		.setParameter("id", customerId)
		.getResultList();
		
		System.out.println(bookings.size());
		return bookings;
	}
}
