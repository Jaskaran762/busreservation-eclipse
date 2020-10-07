package com.lti.repository;

import org.springframework.stereotype.Repository;

@Repository
public class PasswordRepositoryImpl extends GenericRepositoryImpl implements PasswordRepository{

	@Override
	public boolean resetPassword(int customerId,String oldPassword,String newPassword) {
		int count=0;
		String password=(String)entityManager.createQuery("select c.password from Customer c where c.id=:customerId")
				.setParameter("customerId", customerId)
				.getSingleResult();
		System.out.println(password);
		System.out.println(oldPassword);
		if(password.equals(oldPassword)){
			count = entityManager.createQuery("update Customer c set c.password=:newPassword where c.id=:customerId")
				.setParameter("customerId", customerId)
				.setParameter("newPassword", newPassword)
				.executeUpdate();			
			}
		else {
			System.out.println("Old Password doesn't match");
		}
		System.out.println(count);
		return count==1?true:false;
	}
}
