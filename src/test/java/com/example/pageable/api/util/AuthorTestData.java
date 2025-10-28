package com.example.pageable.api.util;

import com.example.pageable.persistence.model.AuthorEntity;
import com.example.pageable.usercesses.dto.AuthorRequestDto;
import com.example.pageable.usercesses.dto.AuthorResponseDto;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class AuthorTestData {

    public static final UUID AUTHOR_ID = UUID.fromString("a1b2c3d4-e5f6-7890-abcd-ef1234567890");
    public static final String NAME = "Говард";
    public static final String SURNAME = "Лавкрафт";

    public static AuthorEntity getAuthorEntity() {
        return AuthorEntity.builder()
                .withAuthorId(AUTHOR_ID)
                .withAuthorName(NAME)
                .withAuthorSurname(SURNAME)
                .build();
    }

    public static AuthorRequestDto getAuthorRequestDto() {
        return AuthorRequestDto.builder()
                .withAuthorName(NAME)
                .withAuthorSurname(SURNAME)
                .build();
    }

    public static AuthorResponseDto getAuthorResponseDto() {
        return AuthorResponseDto.builder()
                .withAuthorId(AUTHOR_ID)
                .withAuthorName(NAME)
                .withAuthorSurname(SURNAME)
                .withBookResponseDtoList(Collections.emptyList())
                .build();
    }
}