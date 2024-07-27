package com.rf.auth_server.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseUser {
    private Long id;
    private String email;
    private String password;
    private String name;
}
