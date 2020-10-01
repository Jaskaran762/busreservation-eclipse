package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.LoginDto;
import com.lti.dto.Status;
import com.lti.entity.Customer;
import com.lti.exception.BusServiceException;
import com.lti.service.BusService;

@RestController
@CrossOrigin
public class LoginController {
	
	@Autowired
	private BusService busService;
	
	@PostMapping(path="/login")
	public Status login(@RequestBody LoginDto loginDto) {
		try {
			Customer customer = busService.login(loginDto.getEmail(), loginDto.getPassword());
			//hello world
			Status status = new Status();
			status.setStatus(true);
			status.setStatusMessage(" Login successful !");
			status.setCustomerId(customer.getId());
			
			return status;
		}
		catch(BusServiceException e) {
			Status status = new Status();
			status.setStatusMessage(e.getMessage());
			status.setStatus(false);
			return status;
		}
	}

}
