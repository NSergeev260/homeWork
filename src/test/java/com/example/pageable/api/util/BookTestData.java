package com.example.pageable.api.util;

import com.example.pageable.persistence.model.BookEntity;
import com.example.pageable.persistence.model.AuthorEntity;
import com.example.pageable.usercesses.dto.BookRequestDto;
import com.example.pageable.usercesses.dto.BookResponseDto;

import java.util.UUID;


public class BookTestData {

    public static final UUID BOOK_ID = UUID.fromString("f6a7b8c9-d0e1-2345-f6a7-b8c9d0e1f2a3");
    public static final String BOOK_TITLE = "История Чарльза Декстера Варда";
    public static final Long SIZE_IN_PAGES = 233L;
    public static final Integer YEAR_OF_PUBLISHING = 2015;
    public static final UUID AUTHOR_ID = UUID.fromString("a1b2c3d4-e5f6-7890-abcd-ef1234567890");

    public static BookEntity getBookEntity(AuthorEntity author) {
        return BookEntity.builder()
                .withBookId(BOOK_ID)
                .withBookTitle(BOOK_TITLE)
                .withSizeInPages(SIZE_IN_PAGES)
                .withYearOfPublishing(YEAR_OF_PUBLISHING)
                .withAuthorEntity(author)
                .build();
    }

    public static BookRequestDto getBookRequestDto() {
        return BookRequestDto.builder()
                .withBookTitle(BOOK_TITLE)
                .withSizeInPages(SIZE_IN_PAGES)
                .withYearOfPublishing(YEAR_OF_PUBLISHING)
                .withAuthorId(AUTHOR_ID)
                .build();
    }

    public static BookResponseDto getBookResponseDto() {
        return BookResponseDto.builder()
                .withBookId(BOOK_ID)
                .withBookTitle(BOOK_TITLE)
                .withSizeInPages(SIZE_IN_PAGES)
                .withYearOfPublishing(YEAR_OF_PUBLISHING)
                .withAuthorResponseDto(AuthorTestData.getAuthorResponseDto())
                .build();
    }
}