package com.lti.service;
 


 import javax.mail.MessagingException;
 
import org.springframework.beans.factory.annotation.Autowired;

 import org.springframework.mail.MailSender;

 import org.springframework.mail.SimpleMailMessage;

 import org.springframework.stereotype.Service;
 
import com.lti.entity.Customer;

 import com.lti.exception.BusServiceException;

 import com.lti.repository.SignUpRepo;
 
@Service

 public class SignUpServiceImpl implements SignUpService{
 
    @Autowired

     public SignUpRepo signUpRepo;


     @Autowired

     private MailSender mailSender;
 
    @Override

     public void register(Customer customer) {

         if(!signUpRepo.isCustomerRegistered(customer.getEmailId())) {

             signUpRepo.save(customer);


             System.out.println("Email Start");

             try {

                 SimpleMailMessage message = new SimpleMailMessage();

                 message.setFrom("BusReservation@outlook.com");

                 message.setTo(customer.getEmailId());

                 message.setSubject("Registration successfull!");

                 message.setText("Greetings "+customer.getName()+" .You are successfully registered. Thankyou for registering with us"

                         + ", enjoy the services");    

                 mailSender.send(message);

                 System.out.println("Sent successfully");

             }

             catch(BusServiceException e) {

                 e.printStackTrace();

             }

         }

         else

             throw new BusServiceException("Customer is already registered!");


     }




 }
	
