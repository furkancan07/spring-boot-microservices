package com.rf.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private int status;
    private String path;
    private Map<String,String> errors=new HashMap<>();
    private String message;
    private LocalDateTime dateTime=LocalDateTime.now();
}
