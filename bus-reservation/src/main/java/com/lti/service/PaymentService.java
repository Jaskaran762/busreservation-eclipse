package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.Booking;
import com.lti.entity.Payment;
import com.lti.exception.BusServiceException;
import com.lti.repository.GenericRepository;
import com.lti.repository.PaymentRepository;
import com.lti.repository.PaymentRepositoryImpl;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepositoryImpl paymentRepository;

	@Transactional
	public void addNewPayment(int id) {
		
		if(id != 0) {
			paymentRepository.add(id);
			
			//need to get the auto generated booking id on clicking Pay
			Booking booking=new Booking();
			
			//insert new record in booking if payment is completed	
			//booking.setBus(id);
			//booking.setCustomer(customer);
			//booking.setDateOfTravel(dateOfTravel);
			//booking.setMobileNumber(mobileNumber);
			//booking.setPanCard(panCard);
			//booking.setPassengers(passengers);
			//booking.setPayment(payment);
			//booking.setSeatsBooked(seatsBooked);
			//booking.setStatus("booked");
			//booking.setTimeOfBooking(LocalDateTime.now());
			//booking.setTravelRoute(travelRoute);
			paymentRepository.save(booking);
		}
		else
			throw new BusServiceException(" Payment couldn't be completed");
	}
}
