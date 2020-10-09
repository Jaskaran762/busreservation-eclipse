package com.lti.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingDto {

	private int bookingId;
	private String busName;
	private int busId;
	private String journeyStartPos;
	private String journeyEndPos;
	private LocalDate dateOfTravel;
	private LocalTime timeOfTravel;
	private int noOfBookings;
	private String bookingStatus;
	
	
	public String getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public String getBusName() {
		return busName;
	}
	public void setBusName(String busName) {
		this.busName = busName;
	}
	public int getBusId() {
		return busId;
	}
	public void setBusId(int busId) {
		this.busId = busId;
	}
	public String getJourneyStartPos() {
		return journeyStartPos;
	}
	public void setJourneyStartPos(String journeyStartPos) {
		this.journeyStartPos = journeyStartPos;
	}
	public String getJourneyEndPos() {
		return journeyEndPos;
	}
	public void setJourneyEndPos(String journeyEndPos) {
		this.journeyEndPos = journeyEndPos;
	}
	public LocalDate getDateOfTravel() {
		return dateOfTravel;
	}
	public void setDateOfTravel(LocalDate dateOfTravel) {
		this.dateOfTravel = dateOfTravel;
	}
	public LocalTime getTimeOfTravel() {
		return timeOfTravel;
	}
	public void setTimeOfTravel(LocalTime timeOfTravel) {
		this.timeOfTravel = timeOfTravel;
	}
	public int getNoOfBookings() {
		return noOfBookings;
	}
	public void setNoOfBookings(int noOfBookings) {
		this.noOfBookings = noOfBookings;
	}
	
	
	
}
