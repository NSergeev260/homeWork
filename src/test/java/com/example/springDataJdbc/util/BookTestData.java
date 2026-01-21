package com.example.springDataJdbc.util;

import com.example.springDataJdbc.persistence.model.BookData;
import com.example.springDataJdbc.usercasses.dto.BookRequestDto;
import com.example.springDataJdbc.usercasses.dto.BookResponseDto;

import java.util.UUID;


public class BookTestData {

    public static final UUID ID = UUID.fromString("f6a7b8c9-d0e1-2345-f6a7-b8c9d0e1f2a3");
    public static final String TITLE = "История Чарльза Декстера Варда";
    public static final String AUTHOR = ("Лавкрафт Говард");
    public static final Integer PUBLICATION_YEAR = 2015;

    public static BookData getBookData() {
        BookData bookData = new BookData();
        bookData.setId(ID);
        bookData.setTitle(TITLE);
        bookData.setAuthor(AUTHOR);
        bookData.setPublicationYear(PUBLICATION_YEAR);
        return bookData;
    }

    public static BookRequestDto getBookRequestDto() {
        return BookRequestDto.builder()
                .withTitle(TITLE)
                .withAuthor(AUTHOR)
                .withPublicationYear(PUBLICATION_YEAR)
                .build();
    }

    public static BookResponseDto getBookResponseDto() {
        return BookResponseDto.builder()
                .withId(ID)
                .withTitle(TITLE)
                .withAuthor(AUTHOR)
                .withPublicationYear(PUBLICATION_YEAR)
                .build();
    }
}