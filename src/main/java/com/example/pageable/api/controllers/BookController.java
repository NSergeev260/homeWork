package com.example.pageable.api.controllers;

import com.example.pageable.usercesses.BookService;
import com.example.pageable.usercesses.dto.BookRequestDto;
import com.example.pageable.usercesses.dto.BookResponseDto;
import jakarta.validation.Valid;
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
    public BookResponseDto addBook(@Valid @RequestBody BookRequestDto bookRequestDto){
        return bookService.addBook(bookRequestDto);
    }

    @GetMapping("/{bookId}")
    public BookResponseDto getBookById(@PathVariable UUID bookId){
        return bookService.getBookById(bookId);
    }

    @GetMapping("/search")
    public Page<BookResponseDto> getBookByTitle(@RequestParam String title, Pageable pageable){
        return bookService.getBookByTitle(title, pageable);
    }

    @GetMapping("/author/{authorId}")
    public Page<BookResponseDto> getBooksByAuthor(@PathVariable UUID authorId, Pageable pageable){
        return bookService.getBooksByAuthor(authorId, pageable);
    }

    @GetMapping
    public Page<BookResponseDto> getAllBooks(Pageable pageable){
        return bookService.getAllBooks(pageable);
    }

    @PutMapping("/{bookId}")
    public BookResponseDto updateBook(@PathVariable UUID bookId,
                                      @Valid @RequestBody BookRequestDto bookRequestDto){
        return bookService.updateBook(bookId, bookRequestDto);
    }

    @DeleteMapping("/{bookId}")
    public void deleteBook(@PathVariable UUID bookId){
        bookService.deleteBook(bookId);
    }
}
