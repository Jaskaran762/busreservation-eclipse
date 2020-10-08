package com.lti.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class BookDto {

	private int seatsBooked;
	private LocalDate dateOfTravel;
	private String travelRoute;
	private String status;
	private String panCard;
	private String mobileNumber;
	private int customerId;
	private int busId;
	private int paymmentId;
	private List<PassengerDto> passengers;

	
	public int getSeatsBooked() {
		return seatsBooked;
	}
	public void setSeatsBooked(int seatsBooked) {
		this.seatsBooked = seatsBooked;
	}
	public LocalDate getDateOfTravel() {
		return dateOfTravel;
	}
	public void setDateOfTravel(LocalDate dateOfTravel) {
		this.dateOfTravel = dateOfTravel;
	}
	public String getTravelRoute() {
		return travelRoute;
	}
	public void setTravelRoute(String travelRoute) {
		this.travelRoute = travelRoute;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPanCard() {
		return panCard;
	}
	public void setPanCard(String panCard) {
		this.panCard = panCard;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getBusId() {
		return busId;
	}
	public void setBusId(int busId) {
		this.busId = busId;
	}
	public int getPaymmentId() {
		return paymmentId;
	}
	public void setPaymmentId(int paymmentId) {
		this.paymmentId = paymmentId;
	}
	public List<PassengerDto> getPassengers() {
		return passengers;
	}
	public void setPassengers(List<PassengerDto> passengers) {
		this.passengers = passengers;
	}
	
	
}
