package com.lti.service;

import com.lti.entity.Customer;

public interface BusService {
	
	// Login 
	Customer login(String email, String password);

}
