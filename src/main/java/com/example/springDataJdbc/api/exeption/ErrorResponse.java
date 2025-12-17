package com.example.springDataJdbc.api.exeption;

public record ErrorResponse(
        int statusCode,
        String message
) {
}

