package com.lti.repository;

import org.springframework.transaction.annotation.Transactional;

public interface GenericRepository {

	<T> T save(Object obj);

	//public Object fetchById(Class clazz, Object id) {
	<T> T fetchById(Class<T> clazz, Object id);

}