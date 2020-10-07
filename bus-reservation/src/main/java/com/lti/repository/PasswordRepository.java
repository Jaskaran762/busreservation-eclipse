package com.lti.repository;

public interface PasswordRepository {

	boolean resetPassword(int customerId,String oldPassword,String newPassword);
	boolean setPassword(int customerId,String newPassword);

}