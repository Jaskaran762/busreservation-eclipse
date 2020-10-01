package com.lti.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.lti.entity.Customer;

@Repository
public class LoginRepositoryImpl extends GenericRepositoryImpl implements LoginRepository{
	
	@Override
	public boolean customerRegistered(String email) {

		return (Long)entityManager.createQuery(" select count(c.id) from Customer c where c.emailId = :email")
				.setParameter("email", email).getSingleResult() == 1 ? true : false;
	}

	@Override
	public int findByEmailAndPassword(String email, String password) {
		return (Integer)entityManager.createQuery(" select c.id from Customer c where c.emailId = :em and c.password = :ps")
				.setParameter("em", email)
				.setParameter("ps", password)
				.getSingleResult();
	}

	@Override
	public Customer findById(int id) {
		return entityManager.find(Customer.class, id);
	}
	
	
	
	
}
