package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.dto.CustomerDto;
import com.lti.entity.Customer;
import com.lti.exception.ProfileServiceException;
import com.lti.repository.ProfileRepository;

@Service
public class ProfileService {

	@Autowired
	private ProfileRepository profileRepository;
	
	public Customer getCustomer(int id) {
		Customer customer = profileRepository.fetchById(Customer.class, id);
		if(customer != null) {
			return customer;
		}
		else
			throw new ProfileServiceException(" No customer with given credentials");
	}
	
	@Transactional
	public boolean updateProfile(CustomerDto customer) {
		
		if(customer != null) {
			return profileRepository.updateProfile(customer);
		}
		else  
			throw new ProfileServiceException(" No such customer exists");
	}
	
	@Transactional
	public void addPic(Customer customer ) {
		profileRepository.save(customer);
	}
	
	public String getUserName(int customerId) {
		return getCustomer(customerId).getName();
	}
	
}
