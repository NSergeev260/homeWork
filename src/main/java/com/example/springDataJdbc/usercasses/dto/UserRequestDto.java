package com.example.springDataJdbc.usercasses.dto;

import com.example.springDataJdbc.persistence.model.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;


@Builder(setterPrefix = "with")
public record UserRequestDto(
        @NotBlank(message = "The name cannot be empty or null")
        String name,
        @Email(message = "The email cannot be empty or null")
        String email,
        @NotBlank(message = "The provider cannot be empty or null")
        String provider,
        @NotBlank(message = "The providerId cannot be empty or null")
        String providerId,
        @NotNull(message = "The role cannot null")
        UserRole role
) {
}
