package com.ecommerce_microservices.api_gateway.configuration;

import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class GatewayConfiguration {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("user-service", predicateSpec -> predicateSpec
                .path("/api/v1/users/**", "/api/v1/auth/**")
                .uri("lb://USER-SERVICE")
            )
            .build();
    }

}
