package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.AdminLoginDto;
import com.lti.dto.Status;
import com.lti.entity.Admin;
import com.lti.exception.BusServiceException;
import com.lti.service.AdminLoginService;

@RestController
@CrossOrigin
public class AdminLoginController {

	@Autowired
	public AdminLoginService adminLoginService;
	
	@PostMapping(path="/adminLogin")
	public Status loginForAdmin(@RequestBody AdminLoginDto adminLoginDto) {
		try {
			Admin admin = adminLoginService.adminLogin(adminLoginDto.getUsername(), adminLoginDto.getPassword());
			
			Status status = new Status();
			status.setStatus(true);
			status.setStatusMessage(" Login Successfull");
			status.setCustomerId(admin.getId());
			
			return status;			
		}
		catch(BusServiceException e) {
			Status status = new Status();
			status.setStatus(false);
			status.setStatusMessage(e.getMessage());
			
			return status;
		}
	}
}
