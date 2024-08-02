package com.rf.product_server.service;

import com.rf.product_server.clients.UserClientService;
import com.rf.product_server.dto.User;
import com.rf.product_server.error.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {
    private final UserClientService clientService;

    public UserService(UserClientService clientService) {
        this.clientService = clientService;
    }
    protected User findByUserId(Long id){
        return clientService.getUser(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Kullanici bulunamadi"));
    }
}
