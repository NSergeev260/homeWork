package com.example.pageable.usercesses.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder(setterPrefix = "with")
public record BookRequestDto(

        @NotBlank(message = "The book title cannot be empty or null")
        String bookTitle,
        @Positive(message = "The size of pages cannot be empty or null")
        Long sizeInPages,
        @Min(value = 1000, message = "Year must be reasonable")
        @Max(value = 2100, message = "Year must be reasonable")
        Integer yearOfPublishing,
        @NotNull(message = "The author id cannot be empty or null")
        UUID authorId
) {
}
