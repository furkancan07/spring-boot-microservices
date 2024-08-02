package com.rf.product_server.clients;

import com.rf.product_server.error.custom.CustomErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class UserClientConfig {
    @Bean
    public ErrorDecoder decoder(){
        return new CustomErrorDecoder();
    }

}
