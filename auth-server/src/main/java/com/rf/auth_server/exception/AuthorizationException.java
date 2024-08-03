package com.rf.auth_server.exception;

public class AuthorizationException extends RuntimeException{
    public AuthorizationException() {
        super("Yetkisiz işlem");
    }
}
