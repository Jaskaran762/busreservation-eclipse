package com.lti.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.dto.BookDto;
import com.lti.dto.BookingDto;
import com.lti.dto.PassengerDto;
import com.lti.entity.AvailableSeat;
import com.lti.entity.Booking;
import com.lti.entity.Bus;
import com.lti.entity.Customer;
import com.lti.entity.Passenger;
import com.lti.repository.BookingRepository;

@Service
public class BookingService {

	@Autowired
	public BookingRepository bookingRepository;
	
	public List<BookingDto> showBookings(int customerId){
		
		List<Booking> bookings = bookingRepository.fetchBookingsByCustomerId(customerId);
		
		List<BookingDto> bookingsDto = new ArrayList<>();
		for(Booking b:bookings) {
			
			BookingDto dto = new BookingDto();
			Bus bus = b.getBus();
			String route = b.getTravelRoute();
			String[] routeElements = route.split(" to ");
			
			dto.setBookingId(b.getId());
			dto.setBusId(bus.getId());
			dto.setBusName(bus.getBusName());
			dto.setDateOfTravel(b.getDateOfTravel());
			dto.setNoOfBookings(b.getSeatsBooked());
			dto.setTimeOfTravel(b.getTimeOfBooking());
			dto.setJourneyStartPos(routeElements[0]);
			dto.setJourneyEndPos(routeElements[1]);
			dto.setBookingStatus(b.getStatus());
			
			bookingsDto.add(dto);
		}
		return bookingsDto;
	}
	
	public int saveBooking(BookDto book) {
		 
		
		List<PassengerDto> passengersDto = book.getPassengers();
		Booking booking = new Booking();
		System.out.println(book.getBusId());
		Bus bus = bookingRepository.fetchById(Bus.class ,book.getBusId());
		System.out.println(bus.getId());
		Customer customer = bookingRepository.fetchById(Customer.class, book.getCustomerId());
		booking.setBus(bus);
		booking.setCustomer(customer);
		booking.setDateOfTravel(book.getDateOfTravel());
		booking.setSeatsBooked(book.getSeatsBooked());
		booking.setStatus(book.getStatus());
		booking.setTimeOfBooking(LocalTime.now());
		booking.setTravelRoute(book.getTravelRoute());
		booking.setPanCard(book.getPanCard());
		booking.setMobileNumber(book.getMobileNumber());
		bookingRepository.save(booking);
		
		List<Passenger> passengers = new ArrayList<>();
		for(PassengerDto pas: passengersDto) {
			Passenger passenger = new Passenger();
			passenger.setAddress(pas.getAddress());
			passenger.setAge(pas.getAge());
			passenger.setGender(pas.getGender());
			passenger.setName(pas.getName());
			int seats = bus.getSeats();
			bus.setSeats(seats-1);
			bookingRepository.save(bus);
			int bookingId = bookingRepository.fetchIdByPanCardDateAndTime(book.getPanCard(), book.getDateOfTravel(),LocalTime.now());
			passenger.setBooking(bookingRepository.fetchById(Booking.class,bookingId));
			bookingRepository.save(passenger);
		}
		return bookingRepository.fetchIdByPanCardDateAndTime(book.getPanCard(), book.getDateOfTravel(),LocalTime.now());
	}
}
