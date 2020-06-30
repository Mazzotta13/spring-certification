package com.alessio.container.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alessio.container.beans.Father;
import com.alessio.container.beans.SonPrototype;
import com.alessio.container.beans.SonSingleton;

@Configuration
public class BeanConfiguration {
	
	@Bean
	public Father father2(SonPrototype son, SonSingleton sonSingleton) {
		return new Father(son, sonSingleton);
	}
	
	@Bean
	public Father father3(SonPrototype son, SonSingleton sonSingleton) {
		return new Father(son, sonSingleton);
	}
}
