package com.alessio.data_management.repository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository<T, D> {
	List<T> findAll();
	List<T> findByName(String name);
	Optional<T> findById(D d);
	T save(T entity);
	String findNameById(Long id);
}
