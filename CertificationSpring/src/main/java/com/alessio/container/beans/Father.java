package com.alessio.container.beans;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.alessio.container.properties.TestProperties;

@Component
@Lazy(value = false) // defaul behaviour
public class Father {
	private SonPrototype son;
	private SonSingleton sonSingleton;
	private String id;
	private TestProperties testProperties;
	
	@Value("${app.valueFromProperties}")
	private String valueFromProperties;
	@Value("${app.valueFromPropertiesDefault:10}")
	private String valueFromPropertiesDefault;
	
	public Father(SonPrototype son, SonSingleton sonSingleton) {
		this.son = son;
		this.sonSingleton = sonSingleton;
		this.id = "id-"+((int) (Math.random() * 1000));
	}
	
	@PostConstruct
	public void hello() {
		System.out.println("Hello [Father], I'am "+id);
		System.out.println("Hello, my son is "+son.getId());
		System.out.println("Hello, my son Singleton is "+sonSingleton.getId());
		setTestProperties(testProperties);
		System.out.println("age: "+testProperties.getAge());
		System.out.println("valueFromProperties: "+valueFromProperties);
		System.out.println("valueFromPropertiesDefault: "+valueFromPropertiesDefault);
	}

	public TestProperties getTestProperties() {
		return testProperties;
	}

	@Autowired
	public void setTestProperties(TestProperties testProperties) {
		this.testProperties = testProperties;
	}
}
