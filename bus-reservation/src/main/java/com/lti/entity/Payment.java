package com.lti.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	private LocalDate paymentDateTime;
	private String status;

	//
	//
	//
	//

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

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public LocalDate getPaymentDateTime() {
		return paymentDateTime;
	}

	public void setPaymentDateTime(LocalDate paymentDateTime) {
		this.paymentDateTime = paymentDateTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
