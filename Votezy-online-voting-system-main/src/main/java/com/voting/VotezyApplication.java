package com.voting;

import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VotezyApplication {
	
	   @Bean
	    public LocalDateTime localDateTime() {
	        return LocalDateTime.now();
	    }

	public static void main(String[] args) {
		SpringApplication.run(VotezyApplication.class, args);
	}

}
