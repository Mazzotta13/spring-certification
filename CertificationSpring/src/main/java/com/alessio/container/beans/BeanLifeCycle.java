package com.alessio.container.beans;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class BeanLifeCycle implements InitializingBean, DisposableBean {
	
	public BeanLifeCycle() {
		System.out.println("BeanLifeCycle: construct");
	}
	
	public void hello() {
		System.out.println("BeanLifeCycle: Hello!!");
	}
	
	@PostConstruct
	public void postConstructMethod() {
		System.out.println("BeanLifeCycle: postConstructMethod");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("BeanLifeCycle: destroy()");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("BeanLifeCycle: afterPropertiesSet");
	}
	
	@PreDestroy
	public void preDestroyMethod() {
		System.out.println("BeanLifeCycle: preDestroyMethod");
	}
	
	
}
