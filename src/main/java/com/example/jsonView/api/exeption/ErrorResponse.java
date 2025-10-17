package com.example.jsonView.api.exeption;

public record ErrorResponse(
        int statusCode,
        String message) {
}