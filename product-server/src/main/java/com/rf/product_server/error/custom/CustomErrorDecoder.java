package com.rf.product_server.error.custom;

import com.rf.product_server.error.exception.AuthorizationException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        switch (response.status()) {
            case 400:
                return new ResponseStatusException(HttpStatus.BAD_REQUEST, "Geçersiz İstek");
            case 401 : return new AuthorizationException();
            case 404:
                return new ResponseStatusException(HttpStatus.NOT_FOUND, "Kullanıcı Bulunamadı");
            case 405:
                return new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "Method Not Allowed");
            default:
                return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Sunucu Hatası");
        }
    }
}
