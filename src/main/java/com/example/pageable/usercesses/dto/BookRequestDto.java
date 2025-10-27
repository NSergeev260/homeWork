package com.example.pageable.usercesses.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder(setterPrefix = "with")
public record BookRequestDto(

        @NotBlank(message = "The book title cannot be empty or null")
        String bookTitle,
        @Positive(message = "The size of pages cannot be empty or null")
        Long sizeInPages,
        @Past(message = "The date of publishing cannot be empty or null")
        Integer yearOfPublishing,
        @NotNull(message = "The author id cannot be empty or null")
        UUID authorId
) {
}
