package com.example.jsonView.usercasses.dto;

import com.example.jsonView.api.view.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Builder(setterPrefix = "with")
public record OrderResponseDto(
        @JsonView(Views.OrderSummary.class)
        UUID orderId,

        @JsonView(Views.OrderDetails.class)
        List<Product> productProduct,

        @JsonView(Views.OrderSummary.class)
        BigDecimal orderAmount,

        @JsonView(Views.OrderSummary.class)
        OrderStatus statusOrder) {
}

// заказ пользователя и содержит информацию о товарах, сумме заказа и статусе.