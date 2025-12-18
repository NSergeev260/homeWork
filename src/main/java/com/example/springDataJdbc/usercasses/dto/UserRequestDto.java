package com.example.springDataJdbc.usercasses.dto;

import com.example.springDataJdbc.persistence.model.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder(setterPrefix = "with")
public record UserRequestDto(
        @NotBlank(message = "The name can not be empty or null")
        String name,
        @Email(message = "The email can not be empty or null")
        String email,
        @NotBlank(message = "The provider can not be empty or null")
        String provider,
        @NotNull(message = "The role can not be null")
        UserRole role
) {
}
