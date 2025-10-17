package com.example.jsonView.usercasses.dto;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder(setterPrefix = "with")
public record UserRequestDto(UUID userId,
                             String userName,
                             String userSurname,
                             String userEmail,
                             List<Order> orderList) {
}
