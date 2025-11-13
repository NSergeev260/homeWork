package com.example.objectMapper.security.dto;

public record LoginRequestDto(
        String username,
        String password
) {
}
