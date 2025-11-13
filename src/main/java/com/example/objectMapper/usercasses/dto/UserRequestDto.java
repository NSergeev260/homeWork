package com.example.objectMapper.usercasses.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder(setterPrefix = "with")
public record UserRequestDto(
        @NotBlank(message = "Username cannot be empty")
        String username,

        @NotBlank(message = "Password cannot be empty")
        @Size(min = 4, message = "Password must be at least 4 characters")
        String password) {
}
