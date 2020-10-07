package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.Status;
import com.lti.entity.Customer;
import com.lti.repository.LoginRepository;
import com.lti.repository.PasswordRepository;
import com.lti.service.EmailService;

@RestController
@CrossOrigin
public class OtpController {
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private PasswordRepository passwordRepository;
	
	//find customer id for email
	@GetMapping(path="/searchCustomer")
	public int searchCustomer(@RequestParam String email) {
		
			int customerId=loginRepository.findByEmail(email);
			return customerId;
	}
	
	@GetMapping(path="/forgotPassword")
	public int forgotPassword(@RequestParam int customerId) {
		
			Customer customer = loginRepository.findById(customerId);
			int generatedOTP=emailService.sendMailToVerifyPassword(customer);
			return generatedOTP;
	}
	@GetMapping(path="/setPassword")
	public Status setPassword(@RequestParam int customerId,String newPassword) {
		
		Status status=new Status();
		if(passwordRepository.setPassword(customerId,newPassword)) {
			System.out.println("True");
			status.setStatus(true);	
			status.setStatusMessage("Password has been reset");
			status.setCustomerId(customerId);
		}
		else {
			System.out.println("Password not updated");
			status.setStatus(false);	
			status.setStatusMessage("Password not updated");
			status.setCustomerId(customerId);
		}
		return status;
	}
}
