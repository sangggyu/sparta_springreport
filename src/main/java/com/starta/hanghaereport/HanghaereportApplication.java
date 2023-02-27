package com.starta.hanghaereport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HanghaereportApplication {

	public static void main(String[] args) {
		SpringApplication.run(HanghaereportApplication.class, args);
	}

}
