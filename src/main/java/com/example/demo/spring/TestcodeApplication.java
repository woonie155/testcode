package com.example.demo.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TestcodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestcodeApplication.class, args);
	}

}
