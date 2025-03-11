package com.ankit.walletwise.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")    // Allow all endpoints
                        .allowedOrigins("http://localhost:5500")  // Your frontend origin
                        .allowedMethods("GET", "POST", "PUT", "DELETE")  // Allowed methods
                        .allowedHeaders("*")   // Allow all headers
                        .allowCredentials(true); // Enable cookies/session sharing (if needed)
            }
        };
    }
}

