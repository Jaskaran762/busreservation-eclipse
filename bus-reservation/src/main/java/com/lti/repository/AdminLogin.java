package com.lti.repository;

import com.lti.entity.Admin;

public interface AdminLogin {
	
	public boolean adminRegistered(String Username);
	
	public int findByUserAndPassword(String Username, String password);
	
	public Admin findById(int id);

}
