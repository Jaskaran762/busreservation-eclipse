package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.entity.Payment;
import com.lti.service.PaymentService;


@RestController
@CrossOrigin
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	@PostMapping(path="/Payment")
	public void newPayment(@RequestBody Payment payment) {
		//get booking id and check in payment table

		//paymentService.addNewPayment(int id);//completed
	}
}
