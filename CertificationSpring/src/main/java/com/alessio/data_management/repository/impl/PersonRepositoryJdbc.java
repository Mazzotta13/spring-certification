package com.alessio.data_management.repository.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.alessio.data_management.entity.Person;
import com.alessio.data_management.repository.PersonRepository;

@Repository
@Profile("jdbc")
public class PersonRepositoryJdbc implements PersonRepository<Person, Long> {
	private JdbcTemplate jdbcTemplate;
	
	public PersonRepositoryJdbc(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public Optional<Person> findById(Long id) {
		RowMapper<Person> rowMapper = new RowMapper<Person>() {
			@Override
			public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
				Person person = new Person();
				person.setAge(rs.getInt("age"));
				person.setId((long) rs.getInt("id"));
				person.setName(rs.getString("name"));
				return person;
			}
		};
		String sql = "SELECT * FROM persons WHERE ID = ?";
		Object[] value = {id};
		Person res = jdbcTemplate.queryForObject(sql, value, rowMapper);
		return Optional.of(res); 
	}

	@Override
	public List<Person> findAll() {
		String sql = "SELECT * FROM persons";
		RowMapper<Person> rowMapper = new RowMapper<Person>() {
			@Override
			public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
				Person person = new Person();
				person.setAge(rs.getInt("age"));
				person.setId((long) rs.getInt("id"));
				person.setName(rs.getString("name"));
				return person;
			}
		};
		List<Person> res = jdbcTemplate.query(sql, rowMapper);
		List<Person> resWithExtractor = jdbcTemplate.query(sql, new ResultSetExtractor<List<Person>>() {
			@Override
			public List<Person> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Person> persons = new ArrayList<>();
				while (rs.next()) {
					Person person = new Person();
					person.setAge(rs.getInt("age"));
					person.setId((long) rs.getInt("id"));
					person.setName(rs.getString("name"));
					persons.add(person);
				} 
				return persons;
			}
			
		});
		
		// this method return void
		jdbcTemplate.query(sql, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				
			}
		});
		if (resWithExtractor.size() != res.size()) System.out.println("ResultSetExtractor e RowMapper risultati diversi");
		return res;
	}

	@Override
	public Person save(Person person) {
		String sql = "INSERT INTO persons (id, name, age) VALUES (?, ?, ?)";
		Object[] params = {person.getId(), person.getName(), person.getAge()}; // parametri per l'inserimento
		jdbcTemplate.update(sql, params);
		return person;
	}

	@Override
	public List<Person> findByName(String name) {
		// prepare statement
		String sql = "SELECT * FROM persons WHERE NAME = ?";
		RowMapper<Person> rowMapper = new RowMapper<Person>() {
			@Override
			public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
				Person person = new Person();
				person.setAge(rs.getInt("age"));
				person.setId((long) rs.getInt("id"));
				person.setName(rs.getString("name"));
				return person;
			}
		};
		PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement preparedStatement) throws SQLException {
				preparedStatement.setString(1, name);
			}
		};
		return jdbcTemplate.query(sql, pss, rowMapper);
	}

	@Override
	public String findNameById(Long id) {
		return "Method not implemented...";
	}
	
	
}
