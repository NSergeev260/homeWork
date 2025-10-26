package com.example.pageable.api.exeption;

public record ErrorResponse(
        int statusCode,
        String message) {
}