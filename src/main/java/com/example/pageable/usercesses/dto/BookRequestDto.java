package com.example.pageable.usercesses.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder(setterPrefix = "with")
public record BookRequestDto(
        String bookTitle,
        Long sizeInPages,
        LocalDate dateOfPublishing
) {
}
