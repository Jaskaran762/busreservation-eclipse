package com.lti.service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entity.Customer;
import com.lti.exception.BusServiceException;
import com.lti.repository.SignUpRepo;

@Service
public class SignUpServiceImpl implements SignUpService{

	@Autowired
	public SignUpRepo signUpRepo;
	

	@Override
	public void register(Customer customer) {
		if(!signUpRepo.isCustomerRegistered(customer.getEmailId())) {
			signUpRepo.save(customer);
			String fromEmail = "do.not.reply.busreservation@gmail.com";
			
			Session session = Session.getDefaultInstance(System.getProperties()); 
			System.out.println("Email Start");
			try {
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress(fromEmail));
				message.addRecipient(Message.RecipientType.TO,new InternetAddress(customer.getEmailId()));
				message.setSubject("Registration successfull!");
				message.setText("Greeting "+customer.getName()+" .You are successfully registered. Thankyou for registering with us"
						+ ", enjoy the services");	
				Transport.send(message);
				System.out.println("Sent successfully");
			}
			catch(MessagingException e) {
				e.printStackTrace();
			}
		}
		else
			throw new BusServiceException("Customer is already registered!");
		
	}
	
	
	
}
