package com.lti.repository;

import com.lti.entity.Booking;

public interface CancelRepository {

	boolean updateStatus(int id);

	double getAmount(int bookingId);
	
	boolean addWallet(double amt,int id);
}