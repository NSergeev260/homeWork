package com.example.jsonView.usercasses.dto;

import com.example.jsonView.persistence.model.Product;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Builder(setterPrefix = "with")
public record OrderRequestDto(
        List<Product> productsList,
        BigDecimal orderAmount,
        OrderStatus orderStatus,
        UUID userId) {
}

// заказ пользователя и содержит информацию о товарах, сумме заказа и статусе.