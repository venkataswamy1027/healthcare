package com.healthcare;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HealthcareApplication {
	private static final Logger logger = LoggerFactory.getLogger(HealthcareApplication.class);

	public static void main(String[] args) {
		logger.info("Started Application {}", System.currentTimeMillis());
		SpringApplication.run(HealthcareApplication.class, args);
	}
}
