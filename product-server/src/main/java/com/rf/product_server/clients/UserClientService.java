package com.rf.product_server.clients;

import com.rf.product_server.Configuration.ClientErrorConfig;
import com.rf.product_server.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "user-service",url = "http://localhost:5861",path = "/api/v1/user",configuration = ClientErrorConfig.class)
public interface UserClientService {
     @GetMapping("/{id}")
     Optional<User> getUser(@PathVariable Long id);

}
