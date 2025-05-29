package com.example.hospitalalert.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
  
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")                     // Para todos os endpoints
                        .allowedOrigins("*")                   // Permite de qualquer origem
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Permite todos esses métodos
                        .allowedHeaders("*")                   // Permite quaisquer cabeçalhos
                        .exposedHeaders("Access-Control-Allow-Origin");  // Expondo o cabeçalho (opcional)
            }
        };
    }
}
