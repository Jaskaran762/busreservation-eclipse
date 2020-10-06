package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.entity.Payment;
import com.lti.service.PaymentService;


@RestController
@CrossOrigin
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	@GetMapping(path="/Payment")
	public Payment newPayment(@RequestParam int busId,int customerId) {
		//get bus id and fetch its amount
		//add new record with that amount in Payment_detail table
		Payment payment=paymentService.addNewPayment(busId,customerId);//completed
		
		int paymentId=payment.getId();
		paymentService.bookingStatus(paymentId);
		
		return payment;
	}
}
