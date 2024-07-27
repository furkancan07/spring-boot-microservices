package com.rf.auth_server.service;

import com.rf.auth_server.clients.UserServiceClient;
import com.rf.auth_server.dto.AuthDto;
import com.rf.auth_server.dto.DtoConverter;
import com.rf.auth_server.dto.LoginRequest;
import com.rf.auth_server.model.BaseUser;
import com.rf.auth_server.model.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final TokenService tokenService;
    private final UserServiceClient client;
    private final DtoConverter converter;
    public AuthDto login(LoginRequest request) {
        BaseUser baseUser=client.getEmailForUser(request.getEmail());
        if(baseUser==null) throw new RuntimeException("Hata");
        if(!baseUser.getPassword().equals(request.getPassword())) throw new RuntimeException("Hata");
        Token token=tokenService.createToken(baseUser.getId());
        AuthDto authDto=AuthDto.builder().token(token.getToken()).user(converter.toUser(baseUser)).build();
        return authDto;
    }
    public void logout(String token){
        tokenService.logout(token);
    }
}
