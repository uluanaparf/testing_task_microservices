package com.example.bookservice.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI bookServiceAPI(){
        return new OpenAPI()
                .info(new Info().title("Book Service API")
                        .description("This is the REST API for Book Service")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0")));
    }
}
