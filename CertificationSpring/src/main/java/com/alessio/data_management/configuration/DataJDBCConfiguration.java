package com.alessio.data_management.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@Profile({"default", "jdbc", "jpa"})
public class DataJDBCConfiguration {
	// for a non in memory database
//	@Bean
//	public DataSource getDataSource() {
//		return DataSourceBuilder.create()
//			.driverClassName("org.h2.Driver")
//			.url("jdbc:h2:mem:test")
//			.username("SA")
//			.password("")
//			.build();
//	}
	
	// in memory database
	@Bean
	public DataSource dataSource() {
	    return new EmbeddedDatabaseBuilder()
	      .setType(EmbeddedDatabaseType.H2)
	      .addScript("classpath:jdbc/schema.sql")
	      .build();
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate(@Autowired DataSource datasource) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(datasource);
		return jdbcTemplate; 
	}
	
	// not necessary with spring boot
	@Profile("jdbc")
	@Bean
	@Qualifier("transactionManager")
	public PlatformTransactionManager transactionManagerJdbc(){
		return new DataSourceTransactionManager(dataSource());
	}
	
	// not necessary with spring boot
	@Profile("jpa")
	@Bean
	@Qualifier("transactionManager")
	public PlatformTransactionManager transactionManagerJpa(){
		return new JpaTransactionManager();
	}
	
	// not necessary with spring boot
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);
		LocalContainerEntityManagerFactoryBean factory = new
		LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("com.alessio.data_management");
		factory.setDataSource(dataSource());
		return factory;
	}
}

