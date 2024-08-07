package com.rf.gateway_service.security;



import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;


@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class WebSecurity {

    private final Filter filter;

    private final CustomAuthenticationEntryPoint entryPoint;
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(csrf -> csrf.disable())
                .headers(h->h.disable())
                .authorizeExchange(authorize -> authorize
                        .pathMatchers("/api/v1/product","/api/v1/product/update/**","/api/v1/product/create/**","/api/v1/product/delete/**").authenticated()
                        .pathMatchers("/swagger-ui/**").permitAll()
                        .anyExchange().permitAll()
                )
                .httpBasic(x->x.authenticationEntryPoint(entryPoint))
                .addFilterBefore(filter,SecurityWebFiltersOrder.AUTHENTICATION)
                .build();
    }

}
