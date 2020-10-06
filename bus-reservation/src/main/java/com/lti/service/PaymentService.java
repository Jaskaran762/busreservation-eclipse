package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.Payment;
import com.lti.exception.BusServiceException;
import com.lti.repository.PaymentRepositoryImpl;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepositoryImpl paymentRepository;

	@Transactional
	public Payment addNewPayment(int busId,int customerId) {
		
		if(busId != 0) {
			Payment payment=paymentRepository.add(busId,customerId);			
			return payment;
		}
		else
			throw new BusServiceException(" Payment couldn't be completed");
	}
	
	@Transactional
	public void bookingStatus(int paymentId) {
		paymentRepository.bookingStatus(paymentId);
	}
}
