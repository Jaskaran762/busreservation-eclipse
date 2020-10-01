package com.lti.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class Bus {

	//create sequence seq_bus start with 10 increment by 10 nocache nocycle
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqBus")
	@SequenceGenerator(name = "seqBus", sequenceName = "seq_bus", allocationSize = 10)
	private int id;
	@Column(name="bus_name")
	private String busName;
	@Column(name="bus_number")
	private String busNumber;
	private double amount;
	private int seats; 
	private String status;
	private String type;
	
	@OneToMany(mappedBy= "bus", cascade= CascadeType.ALL)
	private List<Route> routes;
	
	@OneToMany(mappedBy= "bus",cascade= CascadeType.ALL)
	private List<Booking> bookings;
	
	@OneToMany(mappedBy= "bus",cascade= CascadeType.ALL)
	private List<AvailableSeat> availabeSeats; 
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBusName() {
		return busName;
	}
	public void setBusName(String busName) {
		this.busName = busName;
	}
	public String getBusNumber() {
		return busNumber;
	}
	public void setBusNumber(String busNumber) {
		this.busNumber = busNumber;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
