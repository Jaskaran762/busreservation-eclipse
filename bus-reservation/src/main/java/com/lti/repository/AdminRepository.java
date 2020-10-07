package com.lti.repository;

import java.util.List;

import com.lti.entity.Booking;
import com.lti.entity.Bus;
import com.lti.entity.Customer;
import com.lti.entity.Passenger;
import com.lti.entity.Stop;

public interface AdminRepository extends GenericRepository {

	Bus removeBuses(int id);

	//frequently travelled routes
	List<Object[]> frequentlyTravelledRoutes();

	//passengers without reservation
	List<Passenger> passengersWithoutReservation();

	//reservation details
	Booking booking(int id);

	//registered Customer but no bookings
	List<Customer> noBookings();

	//profits in a month
	double profitInAMonth(int month);

	//most preferred type of buses
	List<Object[]> mostPreferredBuses();

	int findByBusNumber(String number);

	

	List<Object[]> reservationDetailsByYear();

	List<Object[]> reservationDetailsByMonth();

	int findStopByName(String stop);

}