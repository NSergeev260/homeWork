package com.example.objectMapper.security.dto;

public record AuthenticatedUserResponseDto(
        String username,
        String token
) {
}
