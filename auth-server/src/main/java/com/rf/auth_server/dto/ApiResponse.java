package com.rf.auth_server.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashMap;

@Data
@Builder
public class ApiResponse {
    private int status;
    private String message;
    private String path;
    private HashMap<String,String> errors=new HashMap<>();
    private LocalDateTime dateTime;
}
