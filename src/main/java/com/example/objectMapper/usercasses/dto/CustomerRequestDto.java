package com.example.objectMapper.usercasses.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record CustomerRequestDto(

        @NotBlank(message = "First name cannot be empty or null")
        String firstName,
        @NotBlank(message = "Last name cannot be empty or null")
        String lastName,
        @Email(message = "Email must be valid")
        String email,
        @NotBlank(message = "Contact number cannot be empty or null")
        String contactNumber
) {
}
