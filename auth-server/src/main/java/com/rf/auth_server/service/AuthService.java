package com.rf.auth_server.service;

import com.rf.auth_server.clients.UserServiceClient;
import com.rf.auth_server.dto.AuthDto;
import com.rf.auth_server.dto.DtoConverter;
import com.rf.auth_server.dto.LoginRequest;
import com.rf.auth_server.model.Token;
import com.rf.auth_server.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final TokenService tokenService;
    private final UserServiceClient client;
    private final DtoConverter converter;
    public AuthDto login(LoginRequest request) {
        User user =client.authenticate(request);
        if(user ==null) throw new RuntimeException("Email veya şifre yanliş");
        Token token=tokenService.createToken(user.getId());
        AuthDto authDto=AuthDto.builder().token(token.getToken()).user(user).build();
        return authDto;
    }
    public void logout(String token){
        tokenService.logout(token);
    }
}
