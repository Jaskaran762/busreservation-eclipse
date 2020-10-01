package com.lti.test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lti.entity.Booking;
import com.lti.entity.Customer;
import com.lti.entity.Passenger;
import com.lti.entity.Passenger.Gender;
import com.lti.entity.Payment;
import com.lti.repository.GenericRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-config.xml")
public class BookingTest {

	@Autowired
	private GenericRepository genericRepository;
	
	@Test
	public void AddCustomer() {
		Customer customer = new Customer();
		customer.setName("A");
		customer.setUsername("AA");
		customer.setPassword("AA");
		customer.setMobileNo(9111);
		customer.setEmailId("AA@gmail.com");
		
		
		
	}
	
	@Test
	public void fetchCustomer() {
		
	}
	
	@Test
	public void doBooking() {
		Booking booking=new Booking(); 
		booking.setSeatsBooked(2);
		booking.setDateOfTravel(LocalDate.of(2020,10, 11));
		booking.setTimeOfBooking(LocalTime.of(22, 30));
		booking.setTravelRoute("Mumbai to Delhi");
		
		Payment payment=new Payment();
		payment.setAmount(800);
		payment.setPaymentType("wallet");
		payment.setPaymentDateTime(LocalDate.now());
		booking.setPayment(payment);
		
		List<Passenger> passengers=new ArrayList<>();  
		Passenger p1=new Passenger(); 
		p1.setName("A");
		p1.setGender(Gender.Male);
		p1.setBooking(booking);
		
		Passenger p2=new Passenger();      
		p2.setName("B");
		p2.setGender(Gender.Female);
		p2.setBooking(booking);
		
		passengers.add(p1);
		passengers.add(p2);
		booking.setPassengers(passengers);
		
		genericRepository.save(booking);
	}
	
	@Test
	public void fetchBooking() {
		
	}
	
	@Test
	public void fetchPassengers() {
		
	}
}






