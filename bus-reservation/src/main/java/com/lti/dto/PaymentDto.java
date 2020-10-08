package com.lti.dto;

public class PaymentDto {
	private double amount;
	private int busId;
	private int customerId;
	private int bookingId;
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getBusId() {
		return busId;
	}
	public void setBusId(int busId) {
		this.busId = busId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	@Override
	public String toString() {
		return "PaymentDto [amount=" + amount + ", busId=" + busId + ", customerId=" + customerId + ", bookingId="
				+ bookingId + "]";
	}
	
	

}
