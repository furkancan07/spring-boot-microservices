package com.rf.user_service.exception;

public class AuthenticateException extends RuntimeException{
    public AuthenticateException(){
        super("Şifreler Uyuşmuyor");
    }
}
