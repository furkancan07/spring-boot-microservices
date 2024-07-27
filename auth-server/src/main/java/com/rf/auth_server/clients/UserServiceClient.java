package com.rf.auth_server.clients;

import com.rf.auth_server.model.BaseUser;
import com.rf.auth_server.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service",url = "http://localhost:5861",path = "/api/v1/user")
public interface UserServiceClient {
    @GetMapping("/{id}")
    User getUser(@PathVariable Long id);
    @GetMapping("/email/{email}")
    BaseUser getEmailForUser(@PathVariable String email);
}
