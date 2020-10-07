package com.lti.repository;

import com.lti.entity.Customer;

public interface SignUpRepo {
	public boolean isCustomerRegistered(String email);
	
	public void save(Customer customer);

}
