package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entity.Admin;
import com.lti.exception.BusServiceException;
import com.lti.repository.AdminLogin;

@Service
public class AdminLoginServiceImpl implements AdminLoginService {
	
	@Autowired
	public AdminLogin adminLogin;

	@Override
	public Admin adminLogin(String Username, String password) {
		try {
			if(!adminLogin.adminRegistered(Username)) {
				throw new BusServiceException("No admin present with the given username and password");
			}
			int id = adminLogin.findByUserAndPassword(Username, password);
			return adminLogin.findById(id);
		}
		catch(Exception c) {
			throw new BusServiceException("Invalid username or password");
		}
	}
	
	

}
