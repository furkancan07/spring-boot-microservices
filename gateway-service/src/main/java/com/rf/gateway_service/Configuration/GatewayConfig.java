package com.rf.gateway_service.Configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service", r -> r.path("/api/v1/user/**")
                        .uri("http://localhost:5861"))
                .route("product-server", r -> r.path("/api/v1/product/**")
                        .uri("http://localhost:5860"))
                .route("auth-server", r -> r.path("/api/v1/auth/**")
                        .uri("http://localhost:5862"))
                .build();
    }
}
