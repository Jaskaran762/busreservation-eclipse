package com.lti;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lti.entity.AvailableSeat;
import com.lti.entity.Booking;
import com.lti.entity.Bus;
import com.lti.entity.Customer;
import com.lti.entity.Passenger;
import com.lti.entity.Gender;
import com.lti.entity.Route;
import com.lti.entity.Stop;
import com.lti.repository.LoginRepositoryImpl;

@SpringBootTest
class BusReservationApplicationTests {

	@Autowired
	private LoginRepositoryImpl repo;

	@Test
	public void addRoute() {
		
		Customer customer= new Customer();
		customer.setEmailId("hddi@hello.com");
		customer.setMobileNo("98656432227");
		customer.setName("Harsh");
		customer.setPassword("12344");
		repo.save(customer);
		
		List<Route> routeList= new ArrayList<>();
		Route route= new Route();
		route.setArrivalTime(LocalTime.of(8, 30));
		route.setDepartureTime(LocalTime.of(9, 30));
		route.setSequence(1);
		routeList.add(route);
		
		Route route2= new Route();
		route.setArrivalTime(LocalTime.of(10, 00));
		route.setDepartureTime(LocalTime.of(11,00));
		route.setSequence(2);
		routeList.add(route2);
		
		Route route3= new Route();
		route.setArrivalTime(LocalTime.of(12, 00));
		route.setDepartureTime((LocalTime.of(12,30)));
		route.setSequence(3);
		routeList.add(route3);
		
		Route route4 = new Route();
		route.setArrivalTime(LocalTime.of(1,00));
		route.setDepartureTime(LocalTime.of(1,30));
		route.setSequence(4);
		
		Stop stop = new Stop();
		stop.setName("Patiala");
		stop.setRoutes(routeList);
		route.setStop(stop);
		
		Stop stop2 = new Stop();
		stop.setName("Ludhiana");
		stop.setRoutes(routeList);
		route.setStop(stop2);
		
		Stop stop3 = new Stop();
		stop.setName("Jalandhar");
		stop.setRoutes(routeList);
		route.setStop(stop3);
		
		Stop stop4 = new Stop();
		stop.setName("Amritsar");
		stop.setRoutes(routeList);
		route.setStop(stop4);
		
		AvailableSeat availableSeat= new AvailableSeat();
		availableSeat.setDateOfTravel(LocalDate.now());
		availableSeat.setAvailableSeats(20);
		
		AvailableSeat availableSeat2= new AvailableSeat();
		availableSeat2.setDateOfTravel(LocalDate.of(2020, 10, 2));
		availableSeat2.setAvailableSeats(25);
		
		AvailableSeat availableSeat3= new AvailableSeat();
		availableSeat3.setDateOfTravel(LocalDate.of(2020, 10, 3));
		availableSeat3.setAvailableSeats(30);
		
		List<AvailableSeat> availableSeatList= new ArrayList<>();
		availableSeatList.add(availableSeat);
		availableSeatList.add(availableSeat2);
		availableSeatList.add(availableSeat3);
		
		Bus bus = new Bus();
		bus.setAmount(500);
		bus.setAvailabeSeats(availableSeatList);
		bus.setBusName("Pepico Roadways");
		bus.setBusNumber("PB11");
		bus.setRoutes(routeList);
		bus.setSeats(50);
		bus.setStatus("booked");
		bus.setType("AC");
		repo.save(bus);
		route.setBus(bus);
		
		Booking booking= new Booking();
		booking.setBus(bus);
		booking.setDateOfTravel(LocalDate.of(2020, 10, 01));
		booking.setMobileNumber("9855577140");
		booking.setPanCard("GRSVD53H5");
		booking.setSeatsBooked(4);
		booking.setStatus("Booked");
		booking.setTimeOfBooking(LocalTime.now());
		booking.setTravelRoute("Patiala-Amritsar");
		booking.setCustomer(customer);
		repo.save(booking);
		
		Booking booking3= new Booking();
		booking3.setBus(bus);
		booking3.setDateOfTravel(LocalDate.of(2020, 10, 01));
		booking3.setMobileNumber("9855577140");
		booking3.setPanCard("GRSVD53H5");
		booking3.setSeatsBooked(4);
		booking3.setStatus("Booked");
		booking3.setTimeOfBooking(LocalTime.now());
		booking3.setTravelRoute("Patiala-Amritsar");
		booking3.setCustomer(customer);
		
		Booking booking2= new Booking();
		booking2.setBus(bus);
		booking2.setDateOfTravel(LocalDate.of(2020, 10, 01));
		booking2.setMobileNumber("7643897140");
		booking2.setPanCard("YURSVD53H5");
		booking2.setSeatsBooked(2);
		booking2.setStatus("Booked");
		booking2.setTimeOfBooking(LocalTime.now());
		booking2.setTravelRoute("Patiala-Amritsar");
		booking2.setCustomer(customer);
		List<Booking> bookings = new ArrayList<>();
		repo.save(booking2);
		
		Passenger passenger= new Passenger();
		passenger.setAddress("Patiala");
		passenger.setAge(43);
		passenger.setBooking(booking);
		passenger.setGender(Gender.Male);
		passenger.setName("Aman");
		passenger.setSeatNumber(2);

		Passenger passenger2= new Passenger();
		passenger.setAddress("Ludhiana");
		passenger.setAge(33);
		passenger.setBooking(booking);
		passenger.setGender(Gender.Male);
		passenger.setName("Anmol");
		passenger.setSeatNumber(1);
		List<Passenger> passengers= new ArrayList<>();
		passengers.add(passenger);
		passengers.add(passenger2);
		booking.setPassengers(passengers);
		
		
		
		
		repo.save(passenger);
		repo.save(passenger2);
		
		repo.save(availableSeat2);
		repo.save(availableSeat3);
		repo.save(availableSeat);
		repo.save(stop4);
		repo.save(stop3);
		repo.save(stop2);
		repo.save(stop);
		repo.save(route);
		repo.save(route2);
		repo.save(route3);
		repo.save(route4);
		
	}
	
	@Test
	public void addCustomer() {
		Customer customer= new Customer();
		customer.setEmailId("pp@hello.com");
		customer.setMobileNo("98656432227");
		customer.setName("Harsh");
		customer.setPassword("223456789");
		customer.setGender(Gender.Male);
		customer.setDateOfBirth(LocalDate.now());
		repo.save(customer);
	}

}
