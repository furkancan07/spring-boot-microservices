package com.rf.gateway_service.client;

import com.rf.gateway_service.model.User;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service",url = "http://localhost:5861",path = "/api/v1/user")
public interface UserClient {
    @GetMapping("/email/{email}")
    User findByEmail(@PathVariable String email);

}
