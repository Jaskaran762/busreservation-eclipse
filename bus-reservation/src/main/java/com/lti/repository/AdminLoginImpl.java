package com.lti.repository;

import org.springframework.stereotype.Repository;

import com.lti.entity.Admin;

@Repository
public class AdminLoginImpl extends GenericRepositoryImpl implements AdminLogin {

	@Override
	public boolean adminRegistered(String Username) {
		// TODO Auto-generated method stub
		return (Long)entityManager.createQuery(" select count(a.id) from Admin a where a.Username= :username")
				.setParameter("username", Username).getSingleResult() == 1 ? true : false;
	}

	@Override
	public int findByUserAndPassword(String Username, String password) {
		// TODO Auto-generated method stub
		return (Integer)entityManager.createQuery("select a.id from Admin a where a.Username= :user and a.password= :pass")
				.setParameter("user", Username).setParameter("pass", password).getSingleResult();
	}

	@Override
	public Admin findById(int id) {
		// TODO Auto-generated method stub
		return entityManager.find(Admin.class, id);
	}

	
}
