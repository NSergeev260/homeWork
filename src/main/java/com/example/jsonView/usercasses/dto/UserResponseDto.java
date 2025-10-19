package com.example.jsonView.usercasses.dto;

import com.example.jsonView.api.view.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder(setterPrefix = "with")
public record UserResponseDto(
        @JsonView(Views.UserSummary.class)
        UUID userId,

        @JsonView(Views.UserSummary.class)
        String userName,

        @JsonView(Views.UserSummary.class)
        String userSurname,

        @JsonView(Views.UserSummary.class)
        String userEmail,

        @JsonView(Views.UserDetails.class)
        List<OrderResponseDto> orderResponseDtoList) {
}

// имя, адрес электронной почты, идентификатор и т.д.