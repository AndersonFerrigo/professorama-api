 	package com.clearsys.professorama.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class ProfessoramaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProfessoramaApiApplication.class, args);
	}

}

