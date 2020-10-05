package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.LoginDto;
import com.lti.dto.LoginStatus;
import com.lti.entity.Customer;
import com.lti.exception.BusServiceException;
import com.lti.service.BusService;

@RestController
@CrossOrigin
public class LoginController {
	
	@Autowired
	private BusService busService;
	
	@PostMapping(path="/loginCustomer")
	public LoginStatus login(@RequestBody LoginDto loginDto) {
		try {
			Customer customer = busService.login(loginDto.getEmailId(), loginDto.getPassword());
			//hello world
			LoginStatus status = new LoginStatus();
			status.setStatus(true);
			status.setStatusMessage(" Login successful !");
			status.setCustomerId(customer.getId());
			
			return status;
		}
		catch(BusServiceException e) {
			LoginStatus status = new LoginStatus();
			status.setStatusMessage(e.getMessage());
			status.setStatus(false);
			return status;
		}
	}

}
