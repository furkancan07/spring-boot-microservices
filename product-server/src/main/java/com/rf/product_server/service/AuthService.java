package com.rf.product_server.service;

import com.rf.product_server.clients.AuthClientService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthClientService service;
    private final HttpServletRequest request;

    public AuthService(AuthClientService service, HttpServletRequest request) {
        this.service = service;
        this.request = request;
    }
    public Long getIdOfLoggedInUser(){
        String token=getToken();
        if(token==null) return null;
        Long id=service.getIdOfLoggedInUser(token);
        if(id==null) return null;
        return id;
    }
    public String getToken(){
        Cookie[] cookies=request.getCookies();
        if(cookies!=null){
            for (Cookie cookie : cookies){
                if(cookie.getName().equals("login-token") && cookie.getValue()!=null && !cookie.getValue().isEmpty()){
                    return cookie.getValue();
                }
            }
        }

        return null;
    }
}
