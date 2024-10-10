package com.example.apigateway.config;

import com.example.apigateway.filter.AuthenticationFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
public class RouteConfig  {

    private final AuthenticationFilter authenticationFilter;

    public RouteConfig(AuthenticationFilter authenticationFilter) {
        this.authenticationFilter = authenticationFilter;
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route(r -> r.path("/book-service/v3/api-docs").and().method(HttpMethod.GET).uri("lb://BOOK-SERVICE"))
                .route(r -> r.path("/accounting-service/v3/api-docs").and().method(HttpMethod.GET).uri("lb://ACCOUNTING-SERVICE"))
                .route(r -> r.path("/auth-service/v3/api-docs").and().method(HttpMethod.GET).uri("lb://AUTH-SERVICE"))

                .route(r -> r.path("/books").and().method(HttpMethod.GET).filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config()))).uri("lb://BOOK-SERVICE"))
                .route(r -> r.path("/books/{id}").and().method(HttpMethod.GET).filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config()))).uri("lb://BOOK-SERVICE"))
                .route(r -> r.path("/books/isbn/{isbn}").and().method(HttpMethod.GET).filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config()))).uri("lb://BOOK-SERVICE"))
                .route(r -> r.path("/books").and().method(HttpMethod.POST).filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config()))).uri("lb://BOOK-SERVICE"))
                .route(r -> r.path("/books/{id}").and().method(HttpMethod.PUT).filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config()))).uri("lb://BOOK-SERVICE"))
                .route(r -> r.path("/books/{id}").and().method(HttpMethod.DELETE).filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config()))).uri("lb://BOOK-SERVICE"))


                .route(r -> r.path("/accountingBook").and().method(HttpMethod.GET).filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config()))).uri("lb://ACCOUNTING-SERVICE"))
                .route(r -> r.path("/accountingBook/borrowed").and().method(HttpMethod.GET).filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config()))).uri("lb://ACCOUNTING-SERVICE"))
                .route(r -> r.path("/accountingBook/returned").and().method(HttpMethod.GET).filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config()))).uri("lb://ACCOUNTING-SERVICE"))
                .route(r -> r.path("/accountingBook/{id}").and().method(HttpMethod.GET).filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config()))).uri("lb://ACCOUNTING-SERVICE"))
                .route(r -> r.path("/accountingBook/{id}/return-by").and().method(HttpMethod.PUT).filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config()))).uri("lb://ACCOUNTING-SERVICE"))
                .route(r -> r.path("/accountingBook").and().method(HttpMethod.POST).filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config()))).uri("lb://ACCOUNTING-SERVICE"))


                .route(r -> r.path("/auth/register").and().method(HttpMethod.POST).uri("lb://AUTH-SERVICE"))
                .route(r -> r.path("/auth/login").and().method(HttpMethod.POST).uri("lb://AUTH-SERVICE"))
                .route(r -> r.path("/auth/validate").and().method(HttpMethod.GET).uri("lb://AUTH-SERVICE"))
                .build();
    }
}
