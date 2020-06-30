package com.alessio.aop.component;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

@Service
public class TestService {
	
	@PostConstruct
	private void postConstruct() {
		System.out.println("TestService, postConstruct()");
	}
	
	public void adviceTest() {
		System.out.println("TestService, adviceTest()");
	}
	
	public void adviceTestException() {
		System.out.println("TestService, adviceTestException()");
		int x = 1/0; // in order to generete the exception
		System.out.println(x);
	}
}
