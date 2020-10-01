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
	private String mobileNumber;
	
	@ManyToOne(cascade= CascadeType.MERGE)
	@JoinColumn(name= "customer_id")
	private Customer customer;
	
	@ManyToOne(cascade= CascadeType.MERGE)
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
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
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
	
	
}
