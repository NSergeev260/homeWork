package com.example.objectMapper.api.exeption;

public record ErrorResponse(
        int statusCode,
        String message) {
}