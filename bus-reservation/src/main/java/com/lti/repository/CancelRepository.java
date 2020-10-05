package com.lti.repository;

import com.lti.entity.Booking;

public interface CancelRepository {

	Booking updateStatus(int id, String bookingStatus);

}