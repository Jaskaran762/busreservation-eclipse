package com.lti.repository;

import com.lti.entity.Booking;
import com.lti.entity.Bus;
import com.lti.entity.Customer;
import com.lti.entity.Passenger;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class AdminRepositoryImpl extends GenericRepositoryImpl implements AdminRepository {
	
	

	@Override
	public Bus removeBuses(int id) {
		
		entityManager
		.createQuery("update Bus b set b.status='unaivalable' where b.id = :id")
		.setParameter("id", id);
		
		Bus bus= entityManager.find(Bus.class, id);
		return bus;
		
		
	}
	
	//frequently travelled routes
	@Override
	public List<Object[]> frequentlyTravelledRoutes(){
		List<Object[]>frequentlyTravelledRoutes = entityManager.createQuery("select count(b.travelRoute),b.travelRoute from Booking b group by(b.travelRoute) order by count(b.travelRoute) desc").getResultList();
		return frequentlyTravelledRoutes;
		
	}
	
	//passengers without reservation
	@Override
	public List<Passenger> passengersWithoutReservation(){
		List<Passenger> passengers= entityManager.createQuery("select p from Passenger p where p.booking.customer is NULL").getResultList();
		return passengers;
	}
	
	//reservation details
	@Override
	public Booking booking(int id) {
		Booking booking=entityManager.find(Booking.class, id);
		return booking;
	}
	
	//registered Customer but no bookings
	@Override
	public List<Customer> noBookings(){
		List<Integer>customersBooked=entityManager.createQuery("select distinct b.customer.id from Booking b where b.customer is not null").getResultList();
		List<Customer>customers=entityManager.createQuery("select c from Customer c").getResultList();
		
		List<Customer>customersNotBooked=new ArrayList<Customer>();
	
		
		for(Customer c:customers) {
			if(!customersBooked.contains(c.getId())) {
				customersNotBooked.add(c);
							
			}
			
		}
		
		return customersNotBooked;
	}
	
	
	//profits in a month
	@Override
	public double profitInAMonth(int month) {
		return (double)entityManager.createQuery("select sum(p.amount) from Payment p where month(p.paymentDateTime)=:month GROUP BY month(p.paymentDateTime) ").setParameter("month", month).getSingleResult();
	
	}
	
	//most preferred type of buses
	@Override
	public List<Object[]> mostPreferredBuses(){
		List<Object[]> obj= entityManager.createQuery("select count(b.type),b.type from Bus b inner join b.bookings bo group by (b.type) order by count(b.type) DESC").getResultList();
		return obj;
	}
	
	@Override
	public List<Booking> reservationDetailsByMonth(){
		List<Booking> bookingsByMonth= entityManager.createQuery("select b from Booking b where month(b.dateOfTravel)=month(SYSDATE)").getResultList();
		return bookingsByMonth;
	}
	
	@Override
	public List<Booking> reservationDetailsByYear(){
		List<Booking> bookingsByYear= entityManager.createQuery("select b from Booking b where year(b.dateOfTravel)=year(SYSDATE)").getResultList();
		return bookingsByYear;
	}
	@Override
	public String findByBusNumber(String number) {
		
		return (String)entityManager
		.createQuery("select b.busNumber from Bus b where b.busNumber= :number")
		.setParameter("number", number).getSingleResult();

	}
	
	
	
	

}
