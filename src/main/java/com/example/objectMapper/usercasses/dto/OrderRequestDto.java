package com.example.objectMapper.usercasses.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder(setterPrefix = "with")
public record OrderRequestDto(

        @NotNull(message = "Customer cannot be null")
        UUID customerId,

        @NotEmpty(message = "List of products cannot be empty")
        List<@NotBlank String> products,

        @FutureOrPresent(message = "Order date cannot be in the past")
        LocalDateTime orderDate,

        @NotBlank(message = "Shipping address cannot be empty or null")
        String shippingAddress,

        @NotNull(message = "Order status cannot be empty")
        OrderStatus orderStatus
) {
}
