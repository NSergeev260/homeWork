package com.example.jsonView.usercasses.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Builder(setterPrefix = "with")
public record Order(UUID orderId,
                    List<Product> productInfo,
                    BigDecimal orderAmount,
                    OrderStatus statusOrder) {
}

// заказ пользователя и содержит информацию о товарах, сумме заказа и статусе.