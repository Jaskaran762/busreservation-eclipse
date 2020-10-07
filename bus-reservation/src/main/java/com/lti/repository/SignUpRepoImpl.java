package com.lti.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.Customer;

@Repository
public class SignUpRepoImpl extends GenericRepositoryImpl implements SignUpRepo  {

	@Override
	public boolean isCustomerRegistered(String email) {
		return (Long) entityManager.createQuery("select count(c.id) from Customer c where c.emailId= :em")
				.setParameter("em", email).getSingleResult() == 1 ? true : false;
	}

	@Transactional
	@Override
	public void save(Customer customer) {
		entityManager.persist(customer);		
	}

	
}
