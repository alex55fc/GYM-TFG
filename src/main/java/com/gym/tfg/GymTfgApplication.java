package com.gym.tfg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages="com.gym.tfg.dao")
@SpringBootApplication
public class GymTfgApplication {

	public static void main(String[] args) {
		SpringApplication.run(GymTfgApplication.class, args);
	}

}
