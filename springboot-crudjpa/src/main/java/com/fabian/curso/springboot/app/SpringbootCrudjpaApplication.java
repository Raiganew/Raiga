package com.fabian.curso.springboot.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:messages.properties")
public class SpringbootCrudjpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootCrudjpaApplication.class, args);
	}

}
