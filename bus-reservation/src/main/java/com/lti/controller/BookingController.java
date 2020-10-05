package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.BookingDto;
import com.lti.dto.LoginDto;
import com.lti.dto.Status;

import com.lti.entity.Customer;
import com.lti.exception.BusServiceException;
import com.lti.repository.CancelRepository;
import com.lti.service.BookingService;
import com.lti.service.BusService;
import com.lti.service.CancelService;

@RestController
@CrossOrigin
public class BookingController {

	@Autowired
	private CancelService cancelService;

	@Autowired
	private BusService busService;
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private CancelRepository cancelRepository;
	
	@GetMapping(path = "/cancel")
	public Object cancel(@RequestParam int customerId,@RequestParam int bookingId) {
		try {
			Status status = new Status();
			if (cancelService.updateStatus(bookingId)) { //checking if db updated/not updated
				double amt=cancelService.getAmount(bookingId); //get the booking amount, for refund
				cancelService.updateWallet(amt, customerId);  //add it to customer wallet
				status.setCustomerId(customerId);       //display status
				status.setStatus(true);
				status.setStatusMessage("Cancelled");
			}
			
			else { //if db wasn't updated      
				throw new BusServiceException("Booking wasn't cancelled");
			}
			return status;
		} // try

		catch (BusServiceException b) {

			System.out.println(b);
			Status status = new Status();
			status.setStatus(false);
			status.setCustomerId(customerId);
			status.setStatusMessage("Not Cancelled");
			return status;
		}
	}
	
	@PostMapping(path="/showBookings")
	public List<BookingDto> showBookingsOfCustomer(@RequestParam int customerId){
		return bookingService.showBookings(customerId);
	}

}
