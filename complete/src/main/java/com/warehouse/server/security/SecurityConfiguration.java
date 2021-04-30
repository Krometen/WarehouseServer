package com.warehouse.server.security;

import com.sun.istack.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNullApi;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfiguration {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                //Order
                registry.addMapping("/postNewOrder").allowedOrigins("http://localhost:3000").allowedMethods("POST");
                registry.addMapping("/deleteOrder").allowedOrigins("http://localhost:3000").allowedMethods("DELETE");
                registry.addMapping("/getOrders").allowedOrigins("http://localhost:3000").allowedMethods("GET");
                //Product
                registry.addMapping("/postNewProduct").allowedOrigins("http://localhost:3000").allowedMethods("POST");
                registry.addMapping("/deleteProduct").allowedOrigins("http://localhost:3000").allowedMethods("DELETE");
                registry.addMapping("/getProducts").allowedOrigins("http://localhost:3000").allowedMethods("GET");
            }
        };
    }

}
