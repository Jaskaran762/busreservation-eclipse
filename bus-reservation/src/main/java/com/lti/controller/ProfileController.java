package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.lti.dto.Status;
import com.lti.entity.Customer;
import com.lti.service.ProfileService;

public class ProfileController {

	@Autowired
	private ProfileService profileService;

	@PostMapping(path= "/showProfile")
	public Customer showProfile(@RequestParam("customerId") int id) {
		Customer customer = profileService.get(id);
		return customer;
	}
	
	@PostMapping(path="/updateProfile")
	public void updateProfile(@RequestBody Customer customer) {
		profileService.update(customer);
		
	}
}
