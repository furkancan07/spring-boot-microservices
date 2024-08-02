package com.rf.gateway_service.security;

import com.rf.gateway_service.client.TokenService;
import jakarta.servlet.FilterChain;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.session.ReactiveSessionRegistry;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.SessionLimit;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class WebSecurity {

    private final Filter filter;
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(csrf -> csrf.disable())
                .headers(h->h.disable())
                .authorizeExchange(authorize -> authorize
                        .pathMatchers("/api/v1/product","/api/v1/product/update/**","/api/v1/product/create/**","/api/v1/product/delete/**").authenticated()
                        .anyExchange().permitAll()
                )
                .httpBasic(x->x.disable())
                .formLogin(x->x.disable())
                .logout(x->x.disable())
                .addFilterBefore(filter,SecurityWebFiltersOrder.AUTHENTICATION)
                .build();
    }

}
