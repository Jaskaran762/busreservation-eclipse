package com.lti.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="booking")
public class Booking {

	@Id
	@GeneratedValue
	private int id;
	
	@Column(name="customer_id")
	private int customerId;
	
	@Column(name="bus_id")
	private int busId;
	
	@Column(name="seats_booked")
	private int seatsBooked;
	
	@Column(name="date_of_travel")
	private LocalDate dateOfTravel;
	
	@Column(name="time_of_booking")
	private LocalTime timeOfBooking;
	
	@Column(name="travel__route")
	private String travelRoute;

	private String status;
	
	@Column(name="pan_card")
	private String panCard;
	
	@Column(name="mobile_number")
	private int mobileNumber;
	
	@ManyToOne
	@JoinColumn(name= "customer_id")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name= "bus_id")
	private Bus bus;
	
	@OneToOne
	private Payment payment;
	
	@OneToMany(mappedBy="booking", cascade= CascadeType.ALL)
	private List<Passenger> passengers;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public LocalTime getTimeOfBooking() {
		return timeOfBooking;
	}

	public void setTimeOfBooking(LocalTime timeOfBooking) {
		this.timeOfBooking = timeOfBooking;
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

	public int getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(int mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	
}
