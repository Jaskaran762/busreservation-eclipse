package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entity.Customer;
import com.lti.exception.BusServiceException;
import com.lti.repository.LoginRepository;

@Service
public class BusServiceImpl implements BusService {
	
	@Autowired
	private LoginRepository loginRepository;

	@Override
	public Customer login(String email, String password) {
		try {
			if(!loginRepository.customerRegistered(email)) {
				throw new BusServiceException(" Customer is not registered !");
			}
			int id = loginRepository.findByEmailAndPassword(email, password);
			return loginRepository.findById(id);
		}
		catch(Exception c) {
			throw new BusServiceException(" Invalid email or password");
		}
	}
	
	
}
