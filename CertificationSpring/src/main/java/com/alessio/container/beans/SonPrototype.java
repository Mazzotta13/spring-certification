package com.alessio.container.beans;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value="prototype")
public class SonPrototype {
	private String id;
	
	public SonPrototype() {
		this.id = "id-"+((int) (Math.random() * 1000));
	}
	
	@PostConstruct
	public void hello() {
		System.out.println("Hello [SON], I'am "+id);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
