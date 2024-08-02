package com.rf.product_server.error;

import com.rf.product_server.error.dto.ApiResponse;
import com.rf.product_server.error.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // validasyon hataları
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> validationErrorHandler(HttpServletRequest request,MethodArgumentNotValidException ex){
        ApiResponse response=new ApiResponse();
        Map<String,String> errors=new HashMap<>();
        for (FieldError error : ex.getFieldErrors()){
            errors.put(error.getField(),error.getDefaultMessage());
        }
        response=ApiResponse.builder().dateTime(LocalDateTime.now()).errors(errors).message("doğrulama hatası").path(request.getRequestURI()).status(400).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    //not found hataları
    @ExceptionHandler({NotFoundException.class,ResponseStatusException.class})
    public ResponseEntity<ApiResponse> notFoundException(RuntimeException ex,HttpServletRequest request){
        ApiResponse apiResponse=ApiResponse.builder().
                status(404).message(ex.getMessage()).
                dateTime(LocalDateTime.now()).path(request.getRequestURI()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);

    }
    // clientten gelen hatalar


    // bad request hataları
}
