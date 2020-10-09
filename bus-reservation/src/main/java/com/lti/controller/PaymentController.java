package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.PaymentDto;
import com.lti.dto.PaymentStatus;
import com.lti.entity.Payment;
import com.lti.service.PaymentService;


@RestController
@CrossOrigin
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	@PostMapping(path="/Payment")
	public PaymentStatus newPayment(@RequestBody PaymentDto payment) {
		//get bus id and fetch its amount
		//add new record with that amount in Payment_detail table
		System.out.println(payment);
		PaymentStatus status = paymentService.addNewPayment(payment);//completed
		
		return status;
	}
	
	@PostMapping(path="/PaymentById")
	public PaymentStatus fetchPayment(@RequestBody Integer paymentId) {
		PaymentStatus statusById = paymentService.fetchPaymentById(paymentId);
		return statusById;
	}
	

	@PostMapping(path="/PaymentByBookingId")
	public int fetchPaymentByBookingId(@RequestParam("bookingId") int bookingId ) {
		return paymentService.fetchPaymentByBookingId(bookingId);
	}
}
