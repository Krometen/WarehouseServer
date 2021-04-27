package com.warehouse.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.NonNull;
import org.springframework.lang.NonNullApi;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
public class RestServiceCorsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestServiceCorsApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {

			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/greeting").allowedOrigins("http://localhost:3000");
				registry.addMapping("/newOrder").allowedOrigins("http://localhost:3000");
				registry.addMapping("/getOrders").allowedOrigins("http://localhost:3000");
				registry.addMapping("/newOrder").allowedOrigins("http://localhost:3000");
			}
		};
	}
}