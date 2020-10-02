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
import com.lti.repository.CancelRepository;
import com.lti.service.BusService;

@RestController
@CrossOrigin
public class CancelBookingController {

	@Autowired
	private BusService busService;
	
	@Autowired
	private CancelRepository cancelRepository;
	
	@PostMapping(path="/cancel")
	public Object cancel(@RequestBody LoginDto loginDto) {
		try {
		Customer customer= busService.login(loginDto.getEmailId(), loginDto.getPassword());
		int id= customer.getId();
		
//		System.out.println(id);
		//genericRespository.addWallet(id);		
		//need to create a addWallet method, for the customer_id, add refund amount
		//must return wallet balance
		//display wallet balance as alert/window, after clicking confirm button

		Status status=new Status();
		if(cancelRepository.updateStatus(id,"cancelled")!=null) {		
				status.setCustomerId(id);		
				status.setStatus(true);		
				status.setStatusMessage("Cancelled");
		}
		return status;

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
		
	
		}
		
		catch(BusServiceException b) {			

			System.out.println(b);
			Status status=new Status();
			status.setStatus(false);
			status.setStatusMessage("Not Cancelled");
			return status;
		}
	}

}

