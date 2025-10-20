package com.example.jsonView.usercasses.dto;

import com.example.jsonView.api.view.Views;
import com.fasterxml.jackson.annotation.JsonView;

import java.math.BigDecimal;
import java.util.UUID;

public record Product(
        @JsonView(Views.OrderDetails.class)
        UUID productId,

        @JsonView(Views.OrderDetails.class)
        String productName,

        @JsonView(Views.OrderDetails.class)
        BigDecimal productCost) {
}
