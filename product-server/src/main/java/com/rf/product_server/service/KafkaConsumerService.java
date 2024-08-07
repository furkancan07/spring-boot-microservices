package com.rf.product_server.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rf.product_server.entity.Product;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    private final ProductService productService;
    private final ObjectMapper objectMapper;

    public KafkaConsumerService(ProductService productService, ObjectMapper objectMapper) {
        this.productService = productService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "user-delete-event")
    public void getDeleteUserId(ConsumerRecord<String, Product> record) {
        System.out.println(record.value());
        Long id=objectMapper.convertValue(record.value(),Long.class);
        productService.deleteByUserId(id);
    }


}
