package com.lti.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.dto.BookingDto;
import com.lti.entity.Booking;
import com.lti.entity.Bus;
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
			
			bookingsDto.add(dto);
		}
		return bookingsDto;
	}
}
