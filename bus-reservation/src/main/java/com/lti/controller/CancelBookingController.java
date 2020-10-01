package com.lti.controller;

import com.lti.dto.Status;
import com.lti.entity.Customer;

public class CancelBookingController {

	public Status cancel(Customer customer) {
		int id=
		
		Status status=new Status();
		status.setCustomerId(id);
		status.setStatus(true);
		status.setStatusMessage("Cancelled");
		
	}
}
