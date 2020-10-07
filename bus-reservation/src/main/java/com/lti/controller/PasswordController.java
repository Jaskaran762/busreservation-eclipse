package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.Status;
import com.lti.repository.PasswordRepository;

@RestController
@CrossOrigin
public class PasswordController {

	@Autowired
	private PasswordRepository passwordRepository;
	
	@GetMapping(path="/resetPassword")
	public Status resetPassword(@RequestParam int customerId,String oldPassword,String newPassword) {
		Status status=new Status();
		if(passwordRepository.resetPassword(customerId,oldPassword,newPassword)) {
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
