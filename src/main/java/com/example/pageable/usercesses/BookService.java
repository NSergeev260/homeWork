package com.example.pageable.usercesses;

import com.example.pageable.usercesses.dto.AuthorResponseDto;
import com.example.pageable.usercesses.dto.BookRequestDto;
import com.example.pageable.usercesses.dto.BookResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface BookService {
    BookResponseDto addBook(BookRequestDto bookRequestDto);

    BookResponseDto getBookById(UUID bookId);

    Page<BookResponseDto> getBookByTitle(String title, Pageable pageable);

    Page<BookResponseDto> getAllBooks(Pageable pageable);

    Page<BookResponseDto> getBooksByAuthor(UUID authorId, Pageable pageable);

    BookResponseDto updateBook(UUID bookId, BookRequestDto bookRequestDto);

    void deleteBook(UUID bookId);

}
