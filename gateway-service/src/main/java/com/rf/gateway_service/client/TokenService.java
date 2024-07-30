package com.rf.gateway_service.client;

import com.rf.gateway_service.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.CookieValue;


import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "auth-server",url = "http://localhost:5862",path = "/api/v1/auth")
public interface TokenService {
    @GetMapping("/verify")
    User verifyToken(@CookieValue(name = "login-token",required = false) String token);


}
