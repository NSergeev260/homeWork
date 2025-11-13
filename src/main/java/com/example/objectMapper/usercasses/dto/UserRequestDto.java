package com.example.objectMapper.usercasses.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder(setterPrefix = "with")
public record UserRequestDto(
        @NotNull
        @Email(message = "Email must be valid")
        String email,
        @NotNull
        String password) {
}
