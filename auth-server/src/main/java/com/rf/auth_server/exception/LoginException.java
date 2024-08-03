package com.rf.auth_server.exception;

public class LoginException extends RuntimeException{
    public LoginException() {
        super("Email veya Şifre Yanlış");
    }
}
