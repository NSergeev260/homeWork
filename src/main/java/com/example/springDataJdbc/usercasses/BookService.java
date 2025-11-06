package com.example.springDataJdbc.usercasses;

import com.example.springDataJdbc.usercasses.dto.BookRequestDto;
import com.example.springDataJdbc.usercasses.dto.BookResponseDto;

import java.util.UUID;

public interface BookService {

    BookResponseDto insertBook(BookRequestDto bookRequestDto);

    BookResponseDto getBookByID(UUID id);

    BookResponseDto getBookByTitle(String title);

    BookResponseDto updateBook(UUID id, BookRequestDto bookRequestDto);

    void deleteBook(UUID id);
}
