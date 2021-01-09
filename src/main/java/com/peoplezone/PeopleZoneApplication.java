package com.peoplezone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories()
@SpringBootApplication
public class PeopleZoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(PeopleZoneApplication.class, args);
	}

}
