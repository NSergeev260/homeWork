package com.example.springDataProjections.usercasses.dto;

import lombok.Builder;

import java.util.UUID;

@Builder(setterPrefix = "with")
public record DepartmentResponseDto(
        UUID id,
        String name) {
}
