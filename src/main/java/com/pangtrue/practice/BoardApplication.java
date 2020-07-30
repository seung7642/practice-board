package com.pangtrue.practice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@Slf4j
@SpringBootApplication
public class BoardApplication {

	public static void main(String[] args) {
		// TimeZone Setting
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

		SpringApplication.run(BoardApplication.class, args);
	}
}
