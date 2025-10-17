package com.example.jsonView.usercasses.dto;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder(setterPrefix = "with")
public record UserResponseDto(UUID userId,
                              String userName,
                              String userSurname,
                              String userEmail,
                              List<Order> orderList) {
}

// имя, адрес электронной почты, идентификатор и т.д.