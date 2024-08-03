package com.rf.product_server.error.exception;

public class AuthorizationException extends RuntimeException{
    public AuthorizationException() {
        super("Yetkisiz İşlem");
    }
}
