package com.pangtrue.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BoardApplication {

	public static void main(String[] args) {

		// Profile Setting.
		String profile = System.getProperty("spring.profiles.active");
		if (null == profile) {
			System.setProperty("spring.profiles.active", "develop");
		}

		SpringApplication.run(BoardApplication.class, args);
	}

}
