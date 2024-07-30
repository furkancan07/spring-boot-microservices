package com.rf.gateway_service.service;

import com.rf.gateway_service.client.UserClient;
import com.rf.gateway_service.model.User;

import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Service
public class UserService implements ReactiveUserDetailsService {
    private final UserClient client;

    public UserService(UserClient client) {
        this.client = client;
    }


    @Override
    public Mono<UserDetails> findByUsername(String email) {
        User user=client.findByEmail(email);
        if(user==null) throw new RuntimeException("Hata");
        return Mono.just(user).map(u -> new org.springframework.security.core.userdetails.User(
                u.getUsername(),
                u.getPassword(),
                new ArrayList<>()
        ));
    }
}
