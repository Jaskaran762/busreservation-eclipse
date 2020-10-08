package com.lti.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.dto.RouteForBus;
import com.lti.entity.Booking;
import com.lti.entity.Bus;
import com.lti.entity.Customer;
import com.lti.entity.Passenger;
import com.lti.entity.Route;
import com.lti.entity.Stop;
import com.lti.exception.AdminServiceException;
import com.lti.repository.AdminRepository;

@Service
@Transactional
public class AdminService {
	
	@Autowired
	private AdminRepository adminRepository;
	
	//add bus
	public void addBus(Bus bus) {
		//if(adminRepository.findByBusNumber(bus.getBusNumber())!=null) {
			adminRepository.save(bus);
			/*for(RouteForBus route : routes) {
				Route r=new Route();
				r.setStop(adminRepository.fetchById(Stop.class, adminRepository.findStopByName(route.getStop())));
				r.setBus(bus);
				r.setArrivalTime(route.getArrivalTime());
				r.setDepartureTime(route.getArrivalTime());
				r.setSequence(route.getSequence());
				adminRepository.save(r);*/
				
			}
			
	public void addRoute(RouteForBus route) throws ParseException {
		
		DateFormat dateFormat = new SimpleDateFormat("hh:mm");
		Route r=new Route();
		r.setStop(adminRepository.fetchById(Stop.class, adminRepository.findStopByName(route.getStop())));
		r.setBus(route.getBus());
		r.getBus().setId(adminRepository.findByBusNumber(r.getBus().getBusNumber()));
		r.setArrivalTime(route.getArrivalTime());
		r.setDepartureTime(route.getArrivalTime());
		r.setSequence(route.getSequence());
		adminRepository.save(r);

		
		
		
		
		//}
		//else throw new AdminServiceException("A bus with this bus number already exists");
	//
			}
	
	//add Route
	public void addSeparateRoute(Route route) {
		try {
			adminRepository.save(route);	
		} 
		catch(AdminServiceException e) {
			throw new AdminServiceException("Error updating route. Please try later"); 
		 }
	}
	
	//RemoveBus
	/*public Bus removeBus(int id) {
		if(adminRepository.fetchById(Bus.class, id)!=null) {
			Bus bus=adminRepository.removeBuses(id);
			return bus;
		}
		else {
			throw new AdminServiceException("No such buses in the table"); 
		 }
		
	}*/
	
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
	public List<Object[]> reservationDetails(String id) {
		if(adminRepository.bookingDetails(id)!=null) {
			return adminRepository.bookingDetails(id);
		}
		else {
			throw new AdminServiceException("No Data Found");	
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
	public List<Object[]> reservationDetailsByMonth() {
		if(adminRepository.reservationDetailsByMonth()!=null) {
			return adminRepository.reservationDetailsByMonth();
		}
		else {
			throw new AdminServiceException("No bookings in current Month");	
		}
		
	}
	
	//reservation details by year
	public List<Object[]> reservationDetailsByYear() {
		if(adminRepository.reservationDetailsByYear()!=null) {
			return adminRepository.reservationDetailsByYear();
		}
		else {
			throw new AdminServiceException("No bookings in current Year");	
		}
		
	}
	
	public void removeBus(String busNumber) {
		adminRepository.removeBus(busNumber);
	}
}


