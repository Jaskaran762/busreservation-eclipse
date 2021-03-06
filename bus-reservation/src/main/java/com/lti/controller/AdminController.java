package com.lti.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.RouteForBus;
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
	
	@PostMapping(path="/addrouteforbus")
	public @ResponseBody Status addBusRoute(@RequestBody RouteForBus route) throws ParseException {
		System.out.print("shivam   "+route.toString());
		try {
			adminService.addRoute(route);
			Status status = new Status();
			status.setStatus(true);
			status.setStatusMessage("Route added successfully");
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
		return adminService.passengerWithoutRegistration();
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
	
	@PostMapping(path = "/bookingDetails")
	public @ResponseBody List<Object[]> bookingDetails(@RequestParam("id") String id) {
		return adminService.reservationDetails(id);
	}
	
	@PostMapping(path = "/bookingDetailsByMonth")
	public @ResponseBody List<Object[]> bookingDetailsByMonth() {
		return adminService.reservationDetailsByMonth();
	}
	
	@PostMapping(path = "/bookingDetailsByYear")
	public @ResponseBody List<Object[]> bookingDetailsByYear() {
		return adminService.reservationDetailsByYear();
	}
	@PostMapping(path = "/removebus")
	public @ResponseBody void removeBus(@RequestParam("busNumber") String busNumber) {
		adminService.removeBus(busNumber);
	}
	
	/*@PostMapping(path="/addroute")
	public @ResponseBody Status addRoute(@RequestBody RouteForBus route) throws ParseException {
		try {
			adminService.addRoute(route);
			Status status = new Status();
			status.setStatus(true);
			status.setStatusMessage("Route added successfully");
			return status;
		}
		catch(AdminServiceException e) {
			Status status = new Status();
			status.setStatus(false);
			status.setStatusMessage(e.getMessage());
			return status;
			}
		
	}*/
	
	
	
}

