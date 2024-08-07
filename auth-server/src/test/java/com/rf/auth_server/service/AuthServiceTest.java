package com.rf.auth_server.service;

import com.rf.auth_server.clients.UserServiceClient;
import com.rf.auth_server.dto.AuthDto;
import com.rf.auth_server.dto.LoginRequest;
import com.rf.auth_server.model.Token;
import com.rf.auth_server.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class AuthServiceTest {
    @InjectMocks
    private AuthService authService;
    @Mock
    private TokenService tokenService;
    @Mock
    private UserServiceClient client;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login() {
        // arrange
        LoginRequest loginRequest = LoginRequest.builder()
                .email("valid.email@example.com")
                .password("validPassword123")
                .build();
        User user=new User(1L,"ahmet","valid.email@example.com");
        Token token=new Token("some-token",user.getId());

        Mockito.when(client.authenticate(loginRequest)).thenReturn(user);
        Mockito.when(tokenService.createToken(user.getId())).thenReturn(token);

        //act
        AuthDto result=authService.login(loginRequest);
        // Assert
        assertNotNull(result);
        assertEquals(token.getToken(),result.getToken());
        assertEquals(user,result.getUser());


    }

    @Test
    void logout() {
        //Arrange
        String token="some-token";
        //act
        authService.logout(token);
        //assert
        Mockito.verify(tokenService).logout(token);

    }
    @Test
    void testLogout_NoException() {
        // Arrange
        String token = "some-token";

        // Act & Assert
        assertDoesNotThrow(() -> authService.logout(token));
    }

    @Test
    void verifyToken() {
        //Arrange
        String token="same-token";
        User expectedUser=new User(1L,"name","valid.email@example.com");
        Mockito.when(tokenService.verifyToken(token)).thenReturn(expectedUser);
        //act
        User user=authService.verifyToken(token);
        //assert
        assertNotNull(user);
        assertEquals(user,expectedUser);


    }

    @Test
    void getIdOfLoggedInUser() {
        //Arrange
        String token="same-token";
        User expectedUser=new User(1L,"name","valid.email@example.com");

        Mockito.when(tokenService.verifyToken(token)).thenReturn(expectedUser);
        //act
        Long id=authService.getIdOfLoggedInUser(token);
        //assert
        assertNotNull(id);
        assertEquals(id,expectedUser.getId());

    }
}