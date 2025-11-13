package com.example.objectMapper.usercasses.dto;

import com.example.objectMapper.persistence.model.UserRole;
import lombok.Builder;

import java.util.UUID;

@Builder(setterPrefix = "with")
public record UserResponseDto(
        Long id,
        String username,
        UserRole role
) {
}
