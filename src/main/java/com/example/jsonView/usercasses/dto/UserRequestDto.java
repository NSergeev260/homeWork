package com.example.jsonView.usercasses.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder(setterPrefix = "with")
public record UserRequestDto(
        @NotEmpty(message = "UUID cannot be empty or null ")
        UUID userId,
        @NotBlank(message = "Name cannot be empty or null")
        String userName,
        @NotBlank(message = "Surname cannot be empty or null")
        String userSurname,
        @Email(message = "Email must be valid email address")
        String userEmail,

        List<OrderRequestDto> orderRequestDtoList) {
}
