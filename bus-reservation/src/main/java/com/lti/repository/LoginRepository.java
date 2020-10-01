package com.lti.repository;

import com.lti.entity.Customer;

public interface LoginRepository {

	public boolean customerRegistered(String email);

	public int findByEmailAndPassword(String email, String password);
	
	public Customer findById(int id);
	
}
