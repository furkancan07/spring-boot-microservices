package com.rf.product_server.service;

import com.rf.product_server.clients.UserClientService;
import com.rf.product_server.dto.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    @Mock
    private UserClientService client;
    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByUserId() {
        //arrange
        Long id=1L;
        User user= User.builder().name("name").id(id).email("valid@mail.class").build();

        Mockito.when(client.getUser(id)).thenReturn(Optional.of(user));

        //act
        User result=userService.findByUserId(id);
        //assert
        assertNotNull(result);
        assertEquals(user.getId(),result.getId());
        assertEquals(user.getName(),result.getName());
        assertEquals(user.getEmail(),result.getEmail());

    }
}