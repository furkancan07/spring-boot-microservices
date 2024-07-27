package com.rf.auth_server.dto;

import com.rf.auth_server.model.BaseUser;
import com.rf.auth_server.model.User;
import org.springframework.stereotype.Component;

@Component
public class DtoConverter {
    public User toUser(BaseUser baseUser){
        return User.builder().id(baseUser.getId()).email(baseUser.getEmail()).name(baseUser.getName()).build();
    }
}
