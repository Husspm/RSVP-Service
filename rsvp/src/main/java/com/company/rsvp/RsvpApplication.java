package com.company.rsvp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RsvpApplication {

	public static void main(String[] args) {
		SpringApplication.run(RsvpApplication.class, args);
	}

}
