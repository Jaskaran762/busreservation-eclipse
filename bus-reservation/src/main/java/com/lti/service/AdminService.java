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
	
	public void addBus(Bus bus) {
		if(adminRepository.findByBusNumber(bus.getBusNumber())!=null) {
			adminRepository.save(bus);
		}
		else throw new AdminServiceException("A bus with this bus number already exists");
	}
	
	public void addRoute(Route route) {
		try {
			adminRepository.save(route);	
		} 
		catch(AdminServiceException e) {
			throw new AdminServiceException("Error updating route. Please try later"); 
		 }
	}
	
	public Bus removeBus(int id) {
		if(adminRepository.fetchById(Bus.class, id)!=null) {
			Bus bus=adminRepository.removeBuses(id);
			return bus;
		}
		else {
			throw new AdminServiceException("No such buses in the table"); 
		 }
		
	}
	
	public List<Object[]> frequentRoutes(){
		try {
			List<Object[]> frequentRoutes=adminRepository.frequentlyTravelledRoutes();
			return frequentRoutes; 
		}
		catch(AdminServiceException e) {
			throw new AdminServiceException("Can't access the routes right now. please try again later");	
		}
	}
	
	public List<Passenger> passengerWithoutReservation(){
		try {
			List<Passenger> passengersWithoutReservation=adminRepository.passengersWithoutReservation();
			return passengersWithoutReservation;
		}
		catch(AdminServiceException e) {
			throw new AdminServiceException("Failed to access. please try again later");
		}
	}
	
	public Booking reservationDetails(int id) {
		if(adminRepository.booking(id)!=null) {
			return adminRepository.booking(id);
		}
		else {
			throw new AdminServiceException("Please check the booking id again");	
		}
		
	}
	
	public List<Customer> registeredCustomerWithoutBookings(){
		try {
			return adminRepository.noBookings();
		}
		catch(AdminServiceException e) {
			throw new AdminServiceException("Failed to access. please try again later");
		}
	}
	
	public List<Object[]> mostPreferredBusTypes(){
		try {
			return adminRepository.mostPreferredBuses();
		}
		catch(AdminServiceException e) {
			throw new AdminServiceException("Failed to access. please try again later");
		}
	}
	
	public double Profit(int month) {
		try {
			return adminRepository.profitInAMonth(month);
		}
		catch(AdminServiceException e) {
			throw new AdminServiceException("Failed to access. please try again later");
		}
	}
}


