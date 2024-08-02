package com.rf.product_server.error.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    private int status;
    private String path;
    private Map<String,String> errors=new HashMap<>();
    private String message;
    private LocalDateTime dateTime;
}
