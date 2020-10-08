package com.lti.dto;

public class BookingPassengerDto {
	
	private BookDto bookingDto;
	private PassengerDto[] passengerDto;
	public BookDto getBookingDto() {
		return bookingDto;
	}
	public void setBookingDto(BookDto bookingDto) {
		this.bookingDto = bookingDto;
	}
	public PassengerDto[] getPassengerDto() {
		return passengerDto;
	}
	public void setPassengerDto(PassengerDto[] passengerDto) {
		this.passengerDto = passengerDto;
	}
	
}
