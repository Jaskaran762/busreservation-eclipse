package com.lti.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.dto.PaymentDto;
import com.lti.dto.PaymentStatus;
import com.lti.entity.Booking;
import com.lti.entity.Bus;
import com.lti.entity.Payment;
import com.lti.exception.BusServiceException;
import com.lti.repository.PaymentRepositoryImpl;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepositoryImpl paymentRepository;

	@Transactional
	public PaymentStatus addNewPayment(PaymentDto payment) {
		
		Payment pay = new Payment();
		pay.setAmount(payment.getAmount());
		pay.setBooking(paymentRepository.fetchById(Booking.class, payment.getBookingId()));
		LocalDateTime dateTime = LocalDateTime.now();
		pay.setPaymentDateTime(dateTime);
		pay.setPaymentType("Wallet");
		pay.setStatus("Booked");
		Booking booking = paymentRepository.fetchById(Booking.class, payment.getBookingId());
		Bus bus = paymentRepository.fetchById(Bus.class, payment.getBusId());
		if(payment.getBusId() != 0) {
			Payment returnPayment = paymentRepository.save(pay);
			booking.setPayment(returnPayment);
			paymentRepository.save(booking);
			// = paymentRepository.fetchByDateTimeandPanCard(dateTime,booking.getPanCard());
			PaymentStatus status = new PaymentStatus();
			status.setAmount(returnPayment.getAmount());
			status.setBusId(bus.getId());
			status.setBusName(bus.getBusName());
			status.setDateOfTravel(booking.getDateOfTravel());
			status.setMobileNumber(booking.getMobileNumber());
			status.setNumberOfPassengers(booking.getSeatsBooked());
			status.setPaymentDateTime(returnPayment.getPaymentDateTime());
			status.setPaymentId(returnPayment.getId());
			status.setRoute(booking.getTravelRoute());
			status.setStatus(pay.getStatus());
			return status;
			
		}
		else
			throw new BusServiceException(" Payment couldn't be completed");
	}
	
	@Transactional
	public void bookingStatus(int paymentId) {
		paymentRepository.bookingStatus(paymentId);
	}
	
	@Transactional
	public PaymentStatus fetchPaymentById(int paymentId) {
		System.out.println(paymentId);
		Payment payment = paymentRepository.fetchById(Payment.class, paymentId);
		Booking booking = payment.getBooking();
		Bus bus = booking.getBus();
		
		PaymentStatus status = new PaymentStatus();
		status.setAmount(payment.getAmount());
		status.setBusId(bus.getId());
		status.setBusName(bus.getBusName());
		status.setDateOfTravel(booking.getDateOfTravel());
		status.setMobileNumber(booking.getMobileNumber());
		status.setNumberOfPassengers(booking.getSeatsBooked());
		status.setPaymentDateTime(payment.getPaymentDateTime());
		status.setPaymentId(payment.getId());
		status.setRoute(booking.getTravelRoute());
		status.setStatus(payment.getStatus());
		return status;
		
	}
	
}
