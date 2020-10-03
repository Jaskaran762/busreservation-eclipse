package com.lti.repository;

import org.springframework.stereotype.Repository;

import com.lti.dto.CustomerDto;
import com.lti.entity.Customer;

@Repository
public class ProfileRepository extends GenericRepositoryImpl{
	
	public Customer findByEmailPassword(String email, String password) {
		return (Customer)entityManager.createQuery("select c from Customer c where c.emailId=:email and c.password=:password")
				.setParameter("email", email)
				.setParameter("password", password)
				.getSingleResult();
	}
	
	public boolean updateProfile(CustomerDto customer) {
		int row=(Integer)entityManager.createQuery("update Customer c set c.name=:name, c.mobileNo=:mobile, c.emailId=:email,c.gender=:gender,"
		+"c.dateOfBirth=:dob,c.address=:address where c.id=:id")
		.setParameter("name", customer.getName())
		.setParameter("mobile", customer.getMobileNo())
		.setParameter("email", customer.getEmail())
		.setParameter("gender", customer.getGender())
		.setParameter("dob", customer.getDateOfBirth())
		.setParameter("id", customer.getId())
		.setParameter("address", customer.getAddress())
		.executeUpdate();
		return row==1 ? true:false;
	}
}
