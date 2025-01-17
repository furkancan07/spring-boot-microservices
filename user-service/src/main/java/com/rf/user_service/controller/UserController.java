package com.rf.user_service.controller;

import com.rf.user_service.dto.LoginRequest;
import com.rf.user_service.dto.RegisterUserRequest;
import com.rf.user_service.dto.UserDto;
import com.rf.user_service.entity.User;
import com.rf.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    // kayit
    @PostMapping("/register")
   public ResponseEntity<?> register(@RequestBody RegisterUserRequest request){
       return ResponseEntity.ok(userService.register(request));
   }
    // güncelleme
    // silme
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok("Silindi");
    }
    // kullanıcı getirme
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUser(id));
    }

    @GetMapping("email/{email}")
    public ResponseEntity<UserDto> getUser(@PathVariable String email){
        return ResponseEntity.ok(userService.getUserForEmail(email));
    }
    @PostMapping ("/authenticate")
    ResponseEntity<UserDto> authenticate(@RequestBody LoginRequest request){
        return ResponseEntity.ok(userService.authenticate(request));
    }
}
