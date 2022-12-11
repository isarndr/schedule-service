package com.codewithisa.scheduleservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@OpenAPIDefinition(
		servers = {
				@Server(url = "/", description = "Default Server URL")
		}
)

@SpringBootApplication
public class ScheduleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScheduleServiceApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
