package com.example.accountingservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI accountingServiceAPI(){
        return new OpenAPI()
                .info(new Info().title("Accounting Book Service API")
                        .description("This is the REST API for Accounting Book Service")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0")));
    }
}
