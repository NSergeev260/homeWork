package com.example.pageable.usercesses.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder(setterPrefix = "with")
public record BookResponseDto(
        UUID bookId,
        String bookTitle,
        Long sizeInPages,
        LocalDate dateOfPublishing
) {

}
