package com.app.library;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Smoke test suite for the library management API.
 *
 * <p>Boots the full Spring application context to catch configuration and
 * wiring errors early, before any endpoint-level tests run.
 */
@SpringBootTest
class LiabraryApplicationTests {

	/**
	 * Confirms that the Spring application context loads and all beans
	 * (controller, service, and configuration) can be created without error.
	 */
	@Test
	void contextLoads() {
	}

}
