package com.example.pageable.usercesses;

import com.example.pageable.usercesses.dto.AuthorResponseDto;
import com.example.pageable.usercesses.dto.BookResponseDto;

import java.util.List;
import java.util.UUID;

public interface BookService {
    BookResponseDto addBook(BookResponseDto bookResponseDto);
    BookResponseDto getBook(UUID bookId);
    List<BookResponseDto> getAllBook(AuthorResponseDto authorResponseDto);
    BookResponseDto updateBook(UUID bookId);
    void deleteBook(UUID bookId);

}
