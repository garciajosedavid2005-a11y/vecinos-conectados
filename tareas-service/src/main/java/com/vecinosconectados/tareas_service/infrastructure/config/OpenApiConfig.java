package com.vecinosconectados.tareas_service.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI tareasServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Tareas Service API")
                        .description("Microservicio de gestion de tareas - Plataforma Vecinos Conectados")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("Vecinos Conectados")));
    }
}