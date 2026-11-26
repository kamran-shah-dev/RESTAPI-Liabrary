package com.app.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the library management API.
 *
 * <p>Bootstraps the Spring application context and auto-configures the
 * components under {@code com.app.library} (controller, service, and model
 * packages).
 */
@SpringBootApplication
public class LiabraryApplication {

	/**
	 * Launches the application using Spring Boot's configured components.
	 *
	 * @param args command-line arguments forwarded to {@link SpringApplication}
	 */
	public static void main(String[] args) {
		SpringApplication.run(LiabraryApplication.class, args);
	}
}
