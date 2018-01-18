package com.coverter.app.converter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConverterApplication {

	public static void main(String[] args) {
		System.setProperty("server.connection-timeout","60000");
		SpringApplication.run(ConverterApplication.class, args);
	}
}
