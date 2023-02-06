package com.jpmc.theater;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com.jpmc.theater.controller", "com.jpmc.theater.service", "com.jpmc.theater.util",
		"com.jpmc.theater.model" })
public class MovieTheaterApplication {

	public static void main(final String[] args) {
		SpringApplication.run(MovieTheaterApplication.class, args);
	}

}
