package com.rf.product_server.dto;

import com.rf.product_server.entity.Product;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Builder
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private User user;

}
