package com.example.pageable.api.controllers;

import com.example.pageable.usercesses.BookService;
import com.example.pageable.usercesses.dto.BookRequestDto;
import com.example.pageable.usercesses.dto.BookResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/books")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public BookResponseDto addBook(BookRequestDto bookRequestDto){
        return bookService.addBook(bookRequestDto);
    }

    @GetMapping
    public BookResponseDto getBookById(UUID bookId){
        return bookService.getBookById(bookId);
    }

    @GetMapping
    public Page<BookResponseDto> getBookByTitle(String title, Pageable pageable){
        return bookService.getBookByTitle(title, pageable);
    }

    @GetMapping
    public Page<BookResponseDto> getBooksByAuthor(UUID authorId, Pageable pageable){
        return bookService.getBooksByAuthor(authorId, pageable);
    }

    @GetMapping
    public Page<BookResponseDto> getAllBooks(Pageable pageable){
        return bookService.getAllBooks(pageable);
    }

    @PutMapping
    public BookResponseDto updateBook(UUID bookId, BookRequestDto bookRequestDto){
        return bookService.updateBook(bookId, bookRequestDto);
    }

    @DeleteMapping
    public void deleteBook(UUID bookId){
        bookService.deleteBook(bookId);
    }
}
