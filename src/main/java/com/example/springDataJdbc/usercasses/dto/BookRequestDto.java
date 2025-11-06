package com.example.springDataJdbc.usercasses.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder(setterPrefix = "with")
public record BookRequestDto(

        @NotBlank(message = "The book title cannot be empty or null")
        String title,
        @NotBlank(message = "The author cannot be empty or null")
        String author,
        @Min(value = 1000, message = "Year must be reasonable")
        @Max(value = 2100, message = "Year must be reasonable")
        Integer publicationYear
) {
}
