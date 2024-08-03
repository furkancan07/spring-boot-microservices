package com.rf.product_server.clients;

import com.rf.product_server.Configuration.ClientErrorConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "auth-server",url = "http://localhost:5862",path = "/api/v1/auth",configuration = ClientErrorConfig.class)
public interface AuthClientService {
    @GetMapping("/authorized")
    public Long getIdOfLoggedInUser(@CookieValue(name = "login-token",required = false) String token);



}
