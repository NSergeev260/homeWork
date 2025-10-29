package com.example.objectMapper.usercasses.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder(setterPrefix = "with")
public record ProductResponseDto(
        UUID productId,
        String name,
        String description,
        BigDecimal price,
        Long quantityInStock
        ) {
}
