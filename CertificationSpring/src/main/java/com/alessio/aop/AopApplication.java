package com.alessio.aop;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.alessio.aop.component.TestService;
import com.alessio.aop.component.TestServiceAnnotations;

@SpringBootApplication
@ComponentScan(basePackages = "com.alessio.aop")
public class AopApplication {

	public static void main(String[] args) {
		// uso SpringApplicationBuilder per far si che l'app non sia una WebApp
		ConfigurableApplicationContext context = new SpringApplicationBuilder(AopApplication.class)
	        .web(WebApplicationType.NONE) // .REACTIVE, .SERVLET
	        .run(args);
		
		// test advice
		TestService beanTestService = context.getBean(TestService.class);
		beanTestService.adviceTest();
		//beanTestService.adviceTestException();
		
		TestServiceAnnotations beanTestServiceAnnotations = context.getBean(TestServiceAnnotations.class);
		beanTestServiceAnnotations.test();
	}

}