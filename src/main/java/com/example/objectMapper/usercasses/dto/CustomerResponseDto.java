package com.example.objectMapper.usercasses.dto;

import lombok.Builder;

import java.util.UUID;

@Builder(setterPrefix = "with")
public record CustomerResponseDto(
        UUID customerId,
        String firstName,
        String lastName,
        String email,
        String contactNumber
        ) {
}
