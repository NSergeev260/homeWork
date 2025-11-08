package com.example.springDataProjections.api.exeption;

public record ErrorResponse(
        int statusCode,
        String message) {
}