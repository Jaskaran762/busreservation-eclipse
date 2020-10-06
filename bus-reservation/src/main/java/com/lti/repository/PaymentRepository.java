package com.lti.repository;

import com.lti.entity.Payment;

public interface PaymentRepository{

	Payment add(int bus_id,int customerId);
	void bookingStatus(int paymentId); 
}