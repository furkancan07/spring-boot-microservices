package com.rf.auth_server.service;

import com.rf.auth_server.clients.UserServiceClient;
import com.rf.auth_server.model.Token;
import com.rf.auth_server.model.User;
import com.rf.auth_server.repository.TokenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TokenServiceTest {
    @InjectMocks
    private TokenService tokenService;
    @Mock
    private TokenRepository tokenRepository;
    @Mock
    private UserServiceClient client;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createToken() {
        //arrange
        Long userId=1L;
        String tok="same-token";
        Token expectedToken=new Token(tok,userId);

        //act
        Token token=tokenService.createToken(userId);
        //arrange
        assertNotNull(token.getToken());
        assertNotNull(tok);
        assertEquals(userId,token.getUserId());
        Mockito.verify(tokenRepository).save(token);


    }

    @Test
    void verifyToken() {
        //assert
        String tok="same-token";
        Long id=1L;
        Token expectedToken=new Token(tok,id);
        User user=new User(1L,"name","valid@mail.com");
        Mockito.when(client.getUser(expectedToken.getUserId())).thenReturn(user);
        Mockito.when(tokenRepository.findByToken(tok)).thenReturn(Optional.of(expectedToken));
        //act
        User result=tokenService.verifyToken(tok);
        //arrange
        assertEquals(user,result);
    }

    @Test
    void logout() {
        //assert
        String tok="same-token";
        Token expectedToken=new Token(tok,1L);
        Mockito.when(tokenRepository.findByToken(tok)).thenReturn(Optional.of(expectedToken));
        //act
        tokenService.logout(tok);
        // arrange
        Mockito.verify(tokenRepository).delete(expectedToken);

    }
}