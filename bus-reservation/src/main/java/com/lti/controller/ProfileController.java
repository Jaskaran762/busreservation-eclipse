package com.lti.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.CustomerDto;
import com.lti.dto.PicUpload;
import com.lti.dto.Status;
import com.lti.entity.Customer;
import com.lti.service.ProfileService;

@RestController
@CrossOrigin
public class ProfileController {

	@Autowired
	private ProfileService profileService;
	
	@Value("${upload.dir}")
	private String imageUploadLocation;

	@GetMapping(path="/showProfile")
	public CustomerDto showProfile(@RequestParam int id, HttpServletRequest request) {
		Customer customer = profileService.getCustomer(id);
		CustomerDto dto  = new CustomerDto();
		dto.setAddress(customer.getAddress());
		dto.setDateOfBirth(customer.getDateOfBirth());
		dto.setEmail(customer.getEmailId());
		dto.setGender(customer.getGender());
		dto.setId(customer.getId());
		dto.setMobileNo(customer.getMobileNo());
		dto.setName(customer.getName());
		dto.setProfilePic(customer.getProfilePic());
		String projPath = request.getServletContext().getRealPath("/");
		System.out.println(projPath);
		String tempDownloadPath = projPath +"/downloads/";
		File f = new File(tempDownloadPath);
		if(!f.exists())
			f.mkdir();
		String targetFile = tempDownloadPath + customer.getProfilePic();
		String sourceFile = imageUploadLocation + customer.getProfilePic();
		try {
			FileCopyUtils.copy(new File(sourceFile), new File(targetFile));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	@PostMapping(path="/updateProfile")
	public Status updateProfile(@RequestBody CustomerDto customer) {
		boolean result=profileService.updateProfile(customer);
		if(result==true) {
			Status status= new Status();
			status.setStatus(true);
			status.setStatusMessage("Profile Updated Successfully");
			status.setCustomerId(customer.getId());
			return status;
		}
		else {
			Status status= new Status();
			status.setStatus(false);
			status.setStatusMessage("Sorry, problem occured updating your profile");
			status.setCustomerId(customer.getId());
			return status;
		}
	}
	
	@PostMapping("/addPic")
	public Status picUpload(PicUpload picUpload) {
		
		String fileName = picUpload.getProfilePic().getOriginalFilename();
		String targetFile = imageUploadLocation + fileName;
		try {
			FileCopyUtils.copy(picUpload.getProfilePic().getInputStream(), new FileOutputStream(targetFile));
			
		}
		catch(IOException e) {
			e.printStackTrace();
			Status status = new Status();
			status.setStatus(false);
			status.setStatusMessage("Pic upload failed..");
			return status;
		}
		
		Customer customer = profileService.getCustomer(picUpload.getId());
		customer.setProfilePic(fileName);
		profileService.addPic(customer);
		
		Status status = new Status();
		status.setStatus(true);
		status.setStatusMessage("Pic uploaded successfully!");
		return status;
	}
}
