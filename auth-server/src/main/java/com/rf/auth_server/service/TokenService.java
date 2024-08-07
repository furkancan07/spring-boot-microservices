package com.rf.auth_server.service;

import com.rf.auth_server.clients.UserServiceClient;
import com.rf.auth_server.exception.AuthorizationException;
import com.rf.auth_server.model.Token;
import com.rf.auth_server.model.User;
import com.rf.auth_server.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;
    private final UserServiceClient userServiceClient;
    // create
    public Token createToken(Long userId){
        String tok= UUID.randomUUID().toString();
        // buradae client ile user service de bu id ile bir user var mı kontrolü yapılacak
        Token token=Token.builder().token(tok).userId(userId).build();
        tokenRepository.save(token);
        return token;
    }
    // doğrula
    public User verifyToken(String tok){
        Token token=getToken(tok);
        User user=userServiceClient.getUser(token.getUserId());
        return user;
    }
    // sil
    public void logout(String token){
        Token db=getToken(token);
        tokenRepository.delete(db);
    }

    private Token getToken(String tok){
        Token token=tokenRepository.findByToken(tok).orElseThrow(()->new AuthorizationException());
        return token;
    }

}
