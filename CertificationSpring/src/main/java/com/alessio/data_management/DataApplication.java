package com.alessio.data_management;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.alessio.data_management.entity.Person;
import com.alessio.data_management.repository.PersonRepository;
import com.alessio.data_management.transaction.ServiceTransaction;

// remove autoconfiguration datasource
@SpringBootApplication(exclude = {
		DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class})
@ComponentScan(basePackages = "com.alessio.data_management")
public class DataApplication {

	public static void main(String[] args) {
		// default run SpringApplication
//		ConfigurableApplicationContext context = SpringApplication.run(CertificationSpringApplication.class, args);
		
		// uso SpringApplicationBuilder per far si che l'app non sia una WebApp
		ConfigurableApplicationContext context = new SpringApplicationBuilder(DataApplication.class)
	        .web(WebApplicationType.NONE) // .REACTIVE, .SERVLET
	        .run(args);
		
		@SuppressWarnings("unchecked")
		PersonRepository<Person, Long> personRepository = context.getBean(PersonRepository.class);
		personRepository.findAll().forEach(person -> System.out.println(person.toString()));
		personRepository.save(new Person(0L, "Alessio", 26));
		personRepository.save(new Person(1L, "Alessio1", 26));
		personRepository.findAll().forEach(person -> System.out.println(person.toString()));
		personRepository.findByName("Alessio1").forEach(person -> System.out.println(person.toString()));
	
		
		
		// transaction test [transaction annotation]
		ServiceTransaction serviceTransaction = context.getBean(ServiceTransaction.class);
		try {
			serviceTransaction.transactionAnnotation();
		} catch (Exception e) {
			System.out.println("Exception: "+e.getMessage());
		}
		personRepository.findAll().forEach(person -> System.out.println(person.toString()));
		// transaction test [transaction template]
		try {
			serviceTransaction.testTransactiontemplate();
		} catch (Exception e) {
			System.out.println("Exception: "+e.getMessage());
		}
		personRepository.findAll().forEach(person -> System.out.println(person.toString()));
		// transaction test [nested transaction]
		try {
			serviceTransaction.nestedTransaction();
		} catch (Exception e) {
			System.out.println("Exception: "+e.getMessage());
		}
		personRepository.findAll().forEach(person -> System.out.println(person.toString()));
		System.out.println("@Query and @Param [Only JPA]");
		String name = personRepository.findNameById(0L);
		System.out.println("name: "+name);
		
		// chiudere context
//		context.registerShutdownHook(); // chiude context quando la Virtual Machine è spenta
//		context.close(); // chiude context immediatamente
	}

}