package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.lti.entity.Customer;


public class ProfileService {

	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer get(int id) {
		Customer customer = customerRepository.findById(id);
		if(customer != null) {
			return customer;
		}
		else
			throw new CustomerServiceException(" No customer with id "+id);
	}
	
	public void update(Customer customer) {
		
		if(customer != null) {
			customerRepository.update(customer);
		}
		else
			throw new CustomerServiceException(" No such customer exists");
	}
	
	
	
}
