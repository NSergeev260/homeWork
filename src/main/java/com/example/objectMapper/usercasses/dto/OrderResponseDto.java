package com.example.objectMapper.usercasses.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder(setterPrefix = "with")
public record OrderResponseDto(
        UUID orderId,
        CustomerResponseDto customer,
        List<ProductResponseDto> products,
        LocalDateTime orderDate,
        String shippingAddress,
        BigDecimal totalPrice,
        OrderStatus orderStatus
        ) {
}
