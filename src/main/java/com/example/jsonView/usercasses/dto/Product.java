package com.example.jsonView.usercasses.dto;

import com.example.jsonView.api.view.Views;
import com.fasterxml.jackson.annotation.JsonView;

import java.math.BigDecimal;

public record Product(
        @JsonView(Views.OrderDetails.class)
        String name,
        @JsonView(Views.OrderDetails.class)
        BigDecimal cost) {
}
