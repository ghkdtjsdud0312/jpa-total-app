package com.kh.jpatotalapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JpaTotalAppApplication {

	public static void main(String[] args) {

		SpringApplication.run(JpaTotalAppApplication.class, args);
	}

}
