package com.example.jsonView.usercasses.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Builder(setterPrefix = "with")
public record OrderRequestDto(
        List<Product> productsList,
        OrderStatus statusOrder,
        UUID userId) {
}

// заказ пользователя и содержит информацию о товарах, сумме заказа и статусе.