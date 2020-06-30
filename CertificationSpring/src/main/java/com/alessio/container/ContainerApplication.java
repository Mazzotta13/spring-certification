package com.alessio.container;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.alessio.container.beans.BeanLifeCycle;

@SpringBootApplication
@ComponentScan(basePackages = "com.alessio.container")
public class ContainerApplication {

	public static void main(String[] args) {
		// default run SpringApplication
//		ConfigurableApplicationContext context = SpringApplication.run(CertificationSpringApplication.class, args);
		
		// uso SpringApplicationBuilder per far si che l'app non sia una WebApp
		ConfigurableApplicationContext context = new SpringApplicationBuilder(ContainerApplication.class)
	        .web(WebApplicationType.NONE) // .REACTIVE, .SERVLET
	        .run(args);
		
		// ottenere bean dall'application context
		BeanLifeCycle beanLifeCycle = context.getBean(BeanLifeCycle.class);
		beanLifeCycle.hello();
		
		// chiudere context
//		context.registerShutdownHook(); // chiude context quando la Virtual Machine è spenta
//		context.close(); // chiude context immediatamente
	}

}