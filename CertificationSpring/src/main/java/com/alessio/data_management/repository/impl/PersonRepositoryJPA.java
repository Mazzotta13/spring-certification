package com.alessio.data_management.repository.impl;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alessio.data_management.entity.Person;
import com.alessio.data_management.repository.PersonRepository;

@Profile("jpa")
public interface PersonRepositoryJPA extends JpaRepository<Person, Long>, PersonRepository<Person, Long> {
	@Query("select u.name from Person u where u.id = :id")
	String findNameById(Long id);
}
