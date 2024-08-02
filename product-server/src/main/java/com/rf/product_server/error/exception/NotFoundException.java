package com.rf.product_server.error.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String key) {
        super(key+" bulunamadi...:(");
    }
}
