package com.example.springDataJdbc.usercasses.dto;
import lombok.Builder;
import java.time.LocalDate;
import java.util.UUID;

@Builder(setterPrefix = "with")
public record BookResponseDto(
        UUID id,
        String title,
        String author,
        Integer publicationYear
) {
}
