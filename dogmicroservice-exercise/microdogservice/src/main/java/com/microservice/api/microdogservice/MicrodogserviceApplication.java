package com.microservice.api.microdogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicrodogserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicrodogserviceApplication.class, args);
	}

}
