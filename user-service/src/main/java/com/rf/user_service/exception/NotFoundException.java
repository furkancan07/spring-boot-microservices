package com.rf.user_service.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String type){
        super(type+" bulunamadi.Tekrar Dene ...");
    }
}
