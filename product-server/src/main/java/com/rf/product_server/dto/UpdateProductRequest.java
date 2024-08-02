package com.rf.product_server.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Builder
public class UpdateProductRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String description;

    private BigDecimal price;
}
