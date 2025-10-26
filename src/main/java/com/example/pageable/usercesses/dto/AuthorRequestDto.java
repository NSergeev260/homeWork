package com.example.pageable.usercesses.dto;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder(setterPrefix = "with")
public record AuthorRequestDto(
        String name,
        String surname
) {
}
