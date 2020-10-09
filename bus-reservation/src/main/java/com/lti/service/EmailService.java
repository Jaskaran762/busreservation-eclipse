package com.lti.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.lti.entity.Customer;

@Service
public class EmailService {

	@Autowired
	private MailSender mailSender;
	
	public int sendMailToVerifyPassword(Customer customer) {
		Random random=new Random();
		int otp=random.nextInt(10000); //generate a 4 digit otp
		System.out.println(otp);
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("BusReservation@outlook.com");
		//message.setFrom("do.not.reply.busreservation@gmail.com");		
		message.setTo(customer.getEmailId());
		message.setSubject("Bus reservation OTP verification");
		
		 //send otp via email
		message.setText("Greetings" + customer.getName()+".  "+"Your OTP to reset password is: "+otp); 
		mailSender.send(message);
		return otp;
	}

}
