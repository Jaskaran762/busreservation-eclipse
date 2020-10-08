package com.lti.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lti.entity.Booking;
import com.lti.entity.Bus;

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
	
	public int fetchIdByPanCardDateAndTime(String panCard,LocalDate dateOfTravel,LocalTime timeOfBooking) {
		
		return (Integer)entityManager.createQuery("select b.id from Booking b where b.panCard=:panCard and b.dateOfTravel=:dateOfTravel and b.timeOfBooking=:timeOfBooking")
				.setParameter("panCard", panCard)
				.setParameter("dateOfTravel", dateOfTravel)
				.setParameter("timeOfBooking", timeOfBooking)
				.getSingleResult();
	}
}
