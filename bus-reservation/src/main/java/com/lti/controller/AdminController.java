package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.Status;
import com.lti.entity.Booking;
import com.lti.entity.Bus;
import com.lti.entity.Customer;
import com.lti.entity.Passenger;
import com.lti.entity.Route;
import com.lti.exception.AdminServiceException;
import com.lti.service.AdminService;

@CrossOrigin
@RestController
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping(path = "/addbus")
	public @ResponseBody Status addBus(@RequestBody Bus bus) {
		try {
			adminService.addBus(bus);
			Status status = new Status();
			status.setStatus(true);
			status.setStatusMessage("Bus added successfully");
			return status;
		}
		catch(AdminServiceException e) {
			Status status = new Status();
			status.setStatus(false);
			status.setStatusMessage(e.getMessage());
			return status;
			}
		}
		


		
	
	
	@PostMapping(path = "/frequentlytravelledroutes")
	public @ResponseBody List<Object[]> register() {
		return adminService.frequentRoutes();
	}
	
	@PostMapping(path = "/passengerwithoutreservation")
	public @ResponseBody List<Passenger> passengerWithoutReservation() {
		return adminService.passengerWithoutReservation();
	}
	
	@PostMapping(path = "/customerswithoutbooking")
	public @ResponseBody List<Customer> customerWithoutBooking() {
		return adminService.registeredCustomerWithoutBookings();
	}

	@GetMapping(path = "/lastmonthrecordsandprofit")
	public @ResponseBody double profitInAMonth(@RequestParam("month") int month) {
		return adminService.Profit(month);
	}
	
	@PostMapping(path = "/mostpreferredtypeofbuses")
	public @ResponseBody List<Object[]> mostPreferredBuses() {
		return adminService.mostPreferredBusTypes();
	}
	
	@GetMapping(path = "/bookingDetails")
	public @ResponseBody Booking bookingDetails(@RequestParam("id") int id) {
		return adminService.reservationDetails(id);
	}
}