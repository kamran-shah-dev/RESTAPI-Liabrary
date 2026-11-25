package com.app.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** Starts the Spring Boot library management API. */
@SpringBootApplication
public class LiabraryApplication {

	/** Launches the application using Spring Boot's configured components. */
	public static void main(String[] args) {
		SpringApplication.run(LiabraryApplication.class, args);
	}
}
