package com.yamiy.hospsys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.yamiy.hospsys.repositories.mongo")
//@EnableJpaRepositories(basePackages = "com.yamiy.hospsys.repositories.jpa")
public class HospitalSystemApp {

	public static void main(String[] args) {
		SpringApplication.run(HospitalSystemApp.class, args);
	}

}
