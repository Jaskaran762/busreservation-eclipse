package com.lti.service;

import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.Booking;

public interface CancelService {

	boolean updateStatus(int id);

	double getAmount(int bookingId);

	void updateWallet(double amt, int id);

}