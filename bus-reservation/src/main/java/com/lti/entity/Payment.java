package com.lti.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "payment_detail")
public class Payment {
	// create sequence seq_payment start with 1000 increment by 10 nocache nocycle
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqpayment")
	@SequenceGenerator(name = "seqpayment", sequenceName = "seq_payment", allocationSize = 10)
	private int id;

	private double amount;
	@Column(name = "payment_type")
	private String paymentType;
	@Column(name = "payment_date_time")
	private LocalDateTime paymentDateTime;
	private String status;
	
	@OneToOne(mappedBy="payment", cascade= CascadeType.ALL)
	@JoinColumn(name= "booking_id")
	private Booking booking;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public LocalDateTime getPaymentDateTime() {
		return paymentDateTime;
	}

	public void setPaymentDateTime(LocalDateTime paymentDateTime) {
		this.paymentDateTime = paymentDateTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
