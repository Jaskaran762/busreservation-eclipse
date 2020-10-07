package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.CustomerDto;
import com.lti.dto.Status;
import com.lti.entity.Customer;
import com.lti.exception.BusServiceException;
import com.lti.service.SignUpService;

@RestController
@CrossOrigin
public class SignUpController {

	@Autowired 
	public SignUpService signUpService;
	
	@PostMapping(path="/registerCust")
	public Status registerCustomer(@RequestBody Customer customer) {
		try {
			signUpService.register(customer);
			
			Status status = new Status();
			status.setStatus(true);
			status.setStatusMessage("Registration Successfull! Enjoy the services.");
			return status;
		}
		catch(BusServiceException e){
			Status status = new Status();
			
			status.setStatus(false);
			status.setStatusMessage(e.getMessage());
			return status;
		}
		
	}
}
