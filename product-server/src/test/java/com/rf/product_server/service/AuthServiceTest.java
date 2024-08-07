package com.rf.product_server.service;

import com.rf.product_server.clients.AuthClientService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class AuthServiceTest {
    @Mock
    private AuthClientService client;
    @Mock
    private HttpServletRequest request;
    @InjectMocks
    private AuthService authService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getIdOfLoggedInUser() {
        Cookie cookie = new Cookie("login-token", "validToken");
        Cookie[] cookies = {cookie};
        Mockito.when(request.getCookies()).thenReturn(cookies);
        Mockito.when(client.getIdOfLoggedInUser("validToken")).thenReturn(1L);

        // Act
        Long userId = authService.getIdOfLoggedInUser();

        // Assert
        assertNotNull(userId);
        assertEquals(1L, userId);

    }
}