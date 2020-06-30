package com.alessio.container.beans;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class SonSingleton {
	private String id;

	public SonSingleton() {
		this.id = "id-"+((int) (Math.random() * 1000));
	}

	@PostConstruct
	public void hello() {
		System.out.println("Hello [SON_SINGLETON], I'am "+id);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
