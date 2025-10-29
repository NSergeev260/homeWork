package com.example.objectMapper.usercasses.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductRequestDto(

        @NotBlank(message = "Name of product cannot be empty or null")
        String name,

        @NotBlank(message = "Description cannot be empty or null")
        String description,

        @NotNull(message = "Price cannot be null")
        @Positive(message = "Price must be positive")
        BigDecimal price,

        @PositiveOrZero(message = "Quantity must be positive or zero")
        Long quantityInStock
) {
}
