package com.rf.auth_server.controller;

import com.rf.auth_server.clients.UserServiceClient;
import com.rf.auth_server.dto.AuthDto;
import com.rf.auth_server.dto.LoginRequest;
import com.rf.auth_server.model.User;
import com.rf.auth_server.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserServiceClient client;
    private final AuthService authService;
    // login
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request){
        int oneMonth=24*60*60*30;
        AuthDto dto=authService.login(request);
        ResponseCookie cookie=ResponseCookie.from("login-token",dto.getToken()).path("/").maxAge(oneMonth).httpOnly(true).build();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,cookie.toString()).body(dto);
    }
    // logout
    @DeleteMapping("/logout")
    public ResponseEntity<?> logout(@CookieValue(name = "login-token",required = false) String cook){
        authService.logout(cook);
        ResponseCookie cookie=ResponseCookie.from("login-token","").path("/").maxAge(0).httpOnly(true).build();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,cookie.toString()).body("Çıkış Yapildi");
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable Long id){
        return ResponseEntity.ok(client.getUser(id));
    }
    @GetMapping("/verify")
    public ResponseEntity<User> verify(@CookieValue(name = "login-token",required = false) String token){
        return ResponseEntity.ok(authService.verifyToken(token));
    }

}
