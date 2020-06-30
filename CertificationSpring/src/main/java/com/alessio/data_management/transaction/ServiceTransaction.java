package com.alessio.data_management.transaction;

import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.alessio.data_management.entity.Person;
import com.alessio.data_management.repository.PersonRepository;

@Service
public class ServiceTransaction {

	private PersonRepository<Person, Long> personRepository;
	private final TransactionTemplate transactionTemplate;
	
	public ServiceTransaction(PersonRepository<Person, Long> personRepository, 
			PlatformTransactionManager transactionManager) {
		this.personRepository = personRepository;
		this.transactionTemplate = new TransactionTemplate(transactionManager);
	}
	
	@Transactional
	public void transactionAnnotation() throws Exception {
		personRepository.save(new Person(10L, "Ale", 26));
		// exception
		int x = 10/0;
		System.out.println(x);
	}
	
	public void testTransactiontemplate() throws Exception {
		transactionTemplate.setTimeout(1000);
		transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
		transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		transactionTemplate.execute(new TransactionCallback<String>() {
			@Override
			public String doInTransaction(TransactionStatus status) {
				personRepository.save(new Person(11L, "Ale2", 27));
				throw new RuntimeException("ExceptionTest");
			}
		});
	}
	
	@Transactional
	public void nestedTransaction() {
		personRepository.save(new Person(11L, "Ale2", 26));
		childTransaction();
	}
	
	@Transactional
	public void childTransaction() {
		personRepository.save(new Person(10L, "A2", 26));
		throw new RuntimeException("ExceptionTest child");
	}
}
