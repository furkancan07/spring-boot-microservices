package com.rf.user_service.exceptionhandler;

import com.rf.user_service.dto.ApiResponse;
import com.rf.user_service.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse> NotFoundException(HttpServletRequest request,RuntimeException ex){
        ApiResponse apiResponse=new ApiResponse();
        apiResponse=ApiResponse.builder().path(request.getRequestURI()).
        message(ex.getMessage()).status(404).dateTime(LocalDateTime.now()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }
}
