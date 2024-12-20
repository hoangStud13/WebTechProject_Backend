package de.htwberlin.accountservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Erlaubt CORS für alle Pfade
                .allowedOrigins(
                        "http://localhost:8081",  // Entwicklungsumgebung
                        "https://webtechproject-frontend.onrender.com"  // Produktionsumgebung
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
    }
}