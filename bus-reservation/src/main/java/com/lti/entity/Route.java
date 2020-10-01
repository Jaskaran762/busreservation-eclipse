package com.lti.entity;

import java.sql.Time;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name= "route")
public class Route {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	//private int busId//
	//private int stopId//
	@Column(name = "sequence")
	private int sequence;
	
	public Stop getStop() {
		return stop;
	}

	public void setStop(Stop stop) {
		this.stop = stop;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	@Column(name = "arrival_time")
	private Time arrivalTime;
	
	@Column(name = "departure_time")
	private Time departureTime;
	
	@ManyToOne( cascade= CascadeType.ALL)
	@JoinColumn( name = "stop_id")
	private Stop stop;
	
	@ManyToOne( cascade= CascadeType.ALL)
	@JoinColumn( name = "bus_id")
	private Bus bus;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public Time getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Time arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Time getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Time departureTime) {
		this.departureTime = departureTime;
	}
	 
	
	

}
