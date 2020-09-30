package com.lti.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class GenericRepositoryImpl implements GenericRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional
	public <T> T save(Object obj) {
		
		return (T)entityManager.merge(obj);
	}
	
	@Override
	@Transactional
	public <T> T fetchById(Class<T> clazz, Object id) {
		return entityManager.find(clazz, id);
	}
}
