package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.LoginDto;
import com.lti.dto.Status;
import com.lti.entity.Booking;
import com.lti.entity.Customer;
import com.lti.exception.BusServiceException;
import com.lti.service.BusService;

@RestController
@CrossOrigin
public class CancelBookingController {

	@Autowired
	private BusService busService;
	
	@PostMapping(path="/cancel")
	public Object cancel(@RequestBody LoginDto loginDto) {
		try {
		Customer customer= busService.login(loginDto.getEmail(), loginDto.getPassword());
		int id= customer.getId();
		
		//genericRespository.addWallet(id);		
		//need to create a addWallet method, for the customer_id, add refund amount
		//must return wallet balance
		//display wallet balance as alert/window, after clicking confirm button

//		Booking booking=new Booking();
//		booking.setCustomer(customer);
//		//booking.setBus(bus);
//		booking.setDateOfTravel(booking.getDateOfTravel());
//		booking.setTimeOfBooking(booking.getTimeOfBooking());
//		booking.setTravelRoute(booking.getTravelRoute());
//		booking.setStatus("cancelled");
//		booking.setPanCard(booking.getPanCard());
//		booking.setMobileNumber(booking.getMobileNumber());
//		return booking;
		
		Status status=new Status();
		status.setCustomerId(id);
		status.setStatus(true);
		status.setStatusMessage("Cancelled");
		return status;
		}
		
		catch(BusServiceException b) {
			System.out.println(b);
			Status status=new Status();
			status.setStatus(false);
			status.setStatusMessage("Booked");
			return status;
		}
	}

}

