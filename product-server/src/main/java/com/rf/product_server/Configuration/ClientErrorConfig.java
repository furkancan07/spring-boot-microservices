package com.rf.product_server.Configuration;

import com.rf.product_server.error.custom.CustomErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class ClientErrorConfig {
    @Bean
    public ErrorDecoder decoder(){
        return new CustomErrorDecoder();
    }

}
