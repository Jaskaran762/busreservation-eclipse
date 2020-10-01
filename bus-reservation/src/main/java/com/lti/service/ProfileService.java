package com.lti.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.Customer;
import com.lti.exception.ProfileServiceException;
import com.lti.repository.ProfileRepository;

@Service
public class ProfileService {

	@Autowired
	private ProfileRepository profileRepository;
	
	public Customer get(int id) {
		Customer customer = profileRepository.findById(id);
		if(customer != null) {
			return customer;
		}
		else
			throw new ProfileServiceException(" No customer with id "+id);
	}
	
	@Transactional
	public void update(Customer customer) {
		
		if(customer != null) {
			profileRepository.update(customer);
		}
		else
			throw new ProfileServiceException(" No such customer exists");
	}
	
	
	
}
