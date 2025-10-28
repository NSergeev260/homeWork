package com.example.pageable.usercesses.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder(setterPrefix = "with")
public record AuthorRequestDto(
        @NotBlank(message = "The name cannot be empty or null")
        String authorName,
        @NotBlank(message = "The surname cannot be empty or null")
        String authorSurname
) {
}
