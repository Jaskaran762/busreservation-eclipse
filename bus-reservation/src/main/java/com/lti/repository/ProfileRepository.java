package com.lti.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.lti.entity.Customer;

@Repository
public class ProfileRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	public Customer findById(int id) {
		return entityManager.find(Customer.class, id);
	}
	
	public void update(Customer customer) {
		int id= customer.getId();
		entityManager.createQuery("update Customer c set c.name=:name, c.mobileNo=:mobile, c.emailId=:email where c.id=:id")
		.setParameter("name", customer.getName())
		.setParameter("mobile", customer.getMobileNo())
		.setParameter("email", customer.getEmailId())
		.setParameter("id", id)
		.executeUpdate();		
	}
}
