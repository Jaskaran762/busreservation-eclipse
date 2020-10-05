package com.lti.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.Booking;
import com.lti.entity.Bus;
import com.lti.entity.Customer;
import com.lti.entity.Passenger;
import com.lti.entity.Route;
import com.lti.exception.AdminServiceException;
import com.lti.repository.AdminRepository;

@Service
@Transactional
public class AdminService {
	
	@Autowired
	private AdminRepository adminRepository;
	
	//add bus
	public void addBus(Bus bus) {
		if(adminRepository.findByBusNumber(bus.getBusNumber())!=null) {
			adminRepository.save(bus);
		}
		else throw new AdminServiceException("A bus with this bus number already exists");
	}
	
	//add Route
	public void addRoute(Route route) {
		try {
			adminRepository.save(route);	
		} 
		catch(AdminServiceException e) {
			throw new AdminServiceException("Error updating route. Please try later"); 
		 }
	}
	
	//RemoveBus
	public Bus removeBus(int id) {
		if(adminRepository.fetchById(Bus.class, id)!=null) {
			Bus bus=adminRepository.removeBuses(id);
			return bus;
		}
		else {
			throw new AdminServiceException("No such buses in the table"); 
		 }
		
	}
	
	//frequentRoutes
	public List<Object[]> frequentRoutes(){
		try {
			List<Object[]> frequentRoutes=adminRepository.frequentlyTravelledRoutes();
			return frequentRoutes; 
		}
		catch(AdminServiceException e) {
			throw new AdminServiceException("Can't access the routes right now. please try again later");	
		}
	}
	
	//passengersWithoutRegistration
	public List<Passenger> passengerWithoutRegistration(){
		try {
			List<Passenger> passengersWithoutReservation=adminRepository.passengersWithoutReservation();
			return passengersWithoutReservation;
		}
		catch(AdminServiceException e) {
			throw new AdminServiceException("Failed to access. please try again later");
		}
	}
	
	//reservationDetailsByBookingId
	public Booking reservationDetails(int id) {
		if(adminRepository.booking(id)!=null) {
			return adminRepository.booking(id);
		}
		else {
			throw new AdminServiceException("Please check the booking id again");	
		}
		
	}
	
	//RegisteredCustomersWithNoBookings
	public List<Customer> registeredCustomerWithoutBookings(){
		try {
			return adminRepository.noBookings();
		}
		catch(AdminServiceException e) {
			throw new AdminServiceException("Failed to access. please try again later");
		}
	}
	
	//mostPreferredTypesOfBuses
	public List<Object[]> mostPreferredBusTypes(){
		try {
			return adminRepository.mostPreferredBuses();
		}
		catch(AdminServiceException e) {
			throw new AdminServiceException("Failed to access. please try again later");
		}
	}
	
	//profit
	public double Profit(int month) {
		try {
			return adminRepository.profitInAMonth(month);
		}
		catch(AdminServiceException e) {
			throw new AdminServiceException("Failed to access. please try again later");
		}
	}
	
	//reservation details by month
	public List<Booking> reservationDetailsByMonth() {
		if(adminRepository.reservationDetailsByMonth()!=null) {
			return adminRepository.reservationDetailsByMonth();
		}
		else {
			throw new AdminServiceException("No bookings in current Month");	
		}
		
	}
	
	//reservation details by year
	public List<Booking> reservationDetailsByYear() {
		if(adminRepository.reservationDetailsByYear()!=null) {
			return adminRepository.reservationDetailsByYear();
		}
		else {
			throw new AdminServiceException("No bookings in current Year");	
		}
		
	}
}


