package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.Booking;
import com.lti.repository.CancelRepository;

@Service
public class CancelServiceImpl implements CancelService {

	@Autowired
	private CancelRepository cancelRepository;
	
	@Override
	@Transactional
	public boolean updateStatus(int id) {
		return cancelRepository.updateStatus(id);
	}
	
	@Override
	public double getAmount(int bookingId) {
		return cancelRepository.getAmount(bookingId);
	}
	
	@Override
	@Transactional
	public void updateWallet(double amt,int id) {
		cancelRepository.addWallet(amt, id);
	}
}
