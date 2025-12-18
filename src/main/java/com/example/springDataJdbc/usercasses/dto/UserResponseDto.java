package com.example.springDataJdbc.usercasses.dto;

import com.example.springDataJdbc.persistence.model.UserRole;
import lombok.Builder;

import java.util.UUID;

@Builder(setterPrefix = "with")
public record UserResponseDto(
        UUID id,
        String name,
        String email,
        String provider,
        UUID providerId,
        UserRole role
) {
}
