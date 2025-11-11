package com.example.springDataJdbc.api.controllers;

import com.example.springDataJdbc.usercasses.BookService;
import com.example.springDataJdbc.usercasses.dto.BookRequestDto;
import com.example.springDataJdbc.usercasses.dto.BookResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/books")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookResponseDto> addBook(
            @Valid @RequestBody BookRequestDto bookRequestDto) {
        BookResponseDto bookResponseDto = bookService.insertBook(bookRequestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bookResponseDto);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookResponseDto> getBookById(
            @PathVariable UUID bookId) {
        BookResponseDto bookResponseDto = bookService.getBookById(bookId);

        return ResponseEntity
                .ok()
                .body(bookResponseDto);
    }

    @GetMapping("/search")
    public ResponseEntity<BookResponseDto> getBookByTitle(
            @RequestParam String title) {
        BookResponseDto bookResponseDto = bookService.getBookByTitle(title);

        return ResponseEntity
                .ok()
                .body(bookResponseDto);
    }

    @GetMapping("/public")
    public ResponseEntity<List<BookResponseDto>> getAllBooks() {
        List<BookResponseDto> bookResponseDtoList = bookService.getAllBooks();

        return ResponseEntity
                .ok(bookResponseDtoList);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<BookResponseDto> updateBook(
            @PathVariable UUID bookId, @Valid @RequestBody BookRequestDto bookRequestDto) {
        BookResponseDto bookResponseDto = bookService.updateBook(bookId, bookRequestDto);

        return ResponseEntity
                .ok()
                .body(bookResponseDto);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable UUID bookId) {
        bookService.deleteBook(bookId);

        return ResponseEntity
                .noContent()
                .build();
    }
}
