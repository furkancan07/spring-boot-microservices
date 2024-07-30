package com.rf.gateway_service.security;

import com.rf.gateway_service.client.TokenService;
import com.rf.gateway_service.model.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class Filter implements WebFilter {
    private TokenService tokenService;
    private ServerSecurityContextRepository securityContextRepository;

    public Filter(TokenService tokenService, ServerSecurityContextRepository securityContextRepository) {
        this.tokenService = tokenService;
        this.securityContextRepository = securityContextRepository;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        String token = getToken(exchange);
        if (token != null) {
            User user = tokenService.verifyToken(token);
            if (user != null) {
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                SecurityContext securityContext = new SecurityContextImpl(authenticationToken);

                return securityContextRepository.save(exchange, securityContext)
                        .then(chain.filter(exchange));
            }
        }
        return chain.filter(exchange);
    }

    private String getToken(ServerWebExchange exchange) {
        var cookies = exchange.getRequest().getCookies();
        if (cookies != null) {
            var cookie = cookies.getFirst("login-token");
            if (cookie != null && cookie.getValue() != null && !cookie.getValue().isEmpty()) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
