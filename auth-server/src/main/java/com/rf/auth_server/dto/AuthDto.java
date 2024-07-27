package com.rf.auth_server.dto;

import com.rf.auth_server.model.Token;
import com.rf.auth_server.model.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthDto {
    private String token;
    private User user;
}
