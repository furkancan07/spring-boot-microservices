package com.rf.auth_server.service;

import com.rf.auth_server.clients.UserServiceClient;
import com.rf.auth_server.dto.AuthDto;
import com.rf.auth_server.dto.LoginRequest;
import com.rf.auth_server.exception.AuthorizationException;
import com.rf.auth_server.exception.LoginException;
import com.rf.auth_server.model.Token;
import com.rf.auth_server.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final TokenService tokenService;
    private final UserServiceClient client;

    public AuthDto login(LoginRequest request) {
        User user =client.authenticate(request);
        if(user ==null) throw new LoginException();
        Token token=tokenService.createToken(user.getId());
        AuthDto authDto=AuthDto.builder().token(token.getToken()).user(user).build();
        return authDto;
    }
    public void logout(String token){
        tokenService.logout(token);
    }

    public User verifyToken(String token) {
        User user=tokenService.verifyToken(token);
        if (user==null) throw new AuthorizationException();
        return user;
    }

    public Long getIdOfLoggedInUser(String token) {
        User user=tokenService.verifyToken(token);
        if (user==null) throw new AuthorizationException();
        return user.getId();
    }
}
