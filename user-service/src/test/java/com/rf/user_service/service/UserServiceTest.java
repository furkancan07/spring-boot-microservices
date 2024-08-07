package com.rf.user_service.service;

import com.rf.user_service.dto.LoginRequest;
import com.rf.user_service.dto.RegisterUserRequest;
import com.rf.user_service.dto.UserDto;
import com.rf.user_service.entity.User;
import com.rf.user_service.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder encoder;
    @Mock
    private KafkaTemplate kafkaTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void register() {
        // Arrange
        RegisterUserRequest request = RegisterUserRequest.builder()
                .name("name")
                .email("valid@mail.com")
                .password("Ef123456789")
                .build();
        User user = User.builder()
                .id(1L)
                .email(request.getEmail())
                .password("encodedPassword")
                .name(request.getName())
                .build();

        Mockito.when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);
        Mockito.when(encoder.encode(request.getPassword())).thenReturn("encodedPassword");
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        // Act
        UserDto result = userService.register(request);

        // Assert
        assertNotNull(result);  // result null olmamalı
        assertEquals(user.getEmail(), result.getEmail());
        assertEquals(user.getName(), result.getName());
        assertEquals(user.getId(), 1L);

    }

    @Test
    void getUser() {
        //Arrange
        Long id=1L;
        User user=new User(id,"valid@mail.com","encodedPassword","name");
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));
        //act
        UserDto result=userService.getUser(id);
        //Assert
        assertNotNull(result);
        assertEquals(1L,result.getId());
        assertEquals(user.getEmail(),result.getEmail());
        assertEquals(user.getName(),result.getName());

    }

    @Test
    void findByEmail() {
        //arange
        String email="valid@mail.com";
        User user=new User(1L,"valid@mail.com","encodedPassword","name");

        Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        //act
        User result=userService.findByEmail(email);
        //assert
        assertNotNull(result);
        assertEquals(user,result);
    }

    @Test
    void authenticate() {
        //arrange
        LoginRequest request=LoginRequest.builder().email("valid@mail.com").password("Ef123456789").build();
        User user=new User(1L,"valid@mail.com","encodedPassword","name");

        Mockito.when(encoder.matches(request.getPassword(), user.getPassword())).thenReturn(true);
        Mockito.when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(user));

        //act
        UserDto result=userService.authenticate(request);

        //assert
        assertNotNull(result);
        assertEquals(1L,result.getId());
        assertEquals(user.getEmail(),result.getEmail());
        assertEquals(user.getName(),result.getName());

    }



    @Test
    void deleteUser() {
        //arrange
        Long id=1L;
        //act
        userService.deleteUser(id);
        //assert
        Mockito.verify(userRepository,Mockito.times(1)).deleteById(id);
        Mockito.verify(kafkaTemplate,Mockito.times(1)).send("user-delete-event",id);
    }
}