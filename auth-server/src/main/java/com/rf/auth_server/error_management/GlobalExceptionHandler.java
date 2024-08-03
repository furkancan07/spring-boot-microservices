package com.rf.auth_server.error_management;

import com.rf.auth_server.dto.ApiResponse;
import com.rf.auth_server.exception.AuthorizationException;
import com.rf.auth_server.exception.LoginException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // yetkisiz işlem
    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<ApiResponse> authorizationException(RuntimeException ex, HttpServletRequest request) {
        ApiResponse apiResponse = ApiResponse.builder().
                status(401).message(ex.getMessage()).path(request.getRequestURI()).dateTime(LocalDateTime.now()).build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponse);
    }
    // bad request
    @ExceptionHandler(LoginException.class)
    public ResponseEntity<ApiResponse> badRequestException(RuntimeException ex, HttpServletRequest request) {
        ApiResponse apiResponse = ApiResponse.builder().
                status(401).message(ex.getMessage()).path(request.getRequestURI()).dateTime(LocalDateTime.now()).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }
}
