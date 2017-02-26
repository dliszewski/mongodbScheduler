package com.dlis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MongoAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoAppApplication.class, args);
	}
}
