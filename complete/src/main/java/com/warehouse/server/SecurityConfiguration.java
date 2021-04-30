package com.warehouse.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfiguration {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/postNewOrder").allowedOrigins("http://localhost:3000");
                registry.addMapping("/deleteOrder").allowedOrigins("http://localhost:3000");
                registry.addMapping("/getOrders").allowedOrigins("http://localhost:3000");

                registry.addMapping("/postNewProduct").allowedOrigins("http://localhost:3000");
                registry.addMapping("/deleteProduct").allowedOrigins("http://localhost:3000");
                registry.addMapping("/getProducts").allowedOrigins("http://localhost:3000");

            }
        };
    }

}
