package com.example.springDataJdbc.api.controllers;

import com.example.springDataJdbc.usercasses.BookService;
import com.example.springDataJdbc.usercasses.dto.BookRequestDto;
import com.example.springDataJdbc.usercasses.dto.BookResponseDto;
import com.example.springDataJdbc.util.BookTestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @Test
    void methodShouldAddBookTest() {
        BookRequestDto request = BookTestData.getBookRequestDto();
        BookResponseDto response = BookTestData.getBookResponseDto();

        Mockito.when(bookService.insertBook(request))
                .thenReturn(response);

        ResponseEntity<BookResponseDto> result = bookController.addBook(request);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.CREATED,
                result.getStatusCode());
        Assertions.assertEquals(response.id(),
                result.getBody().id());
        Assertions.assertEquals(response.title(),
                result.getBody().title());
        Mockito.verify(bookService).insertBook(request);
    }

    @Test
    void methodShouldGetBookByIdTest() {
        BookResponseDto response = BookTestData.getBookResponseDto();
        UUID bookId = BookTestData.ID;

        Mockito.when(bookService.getBookById(bookId))
                .thenReturn(response);

        ResponseEntity<BookResponseDto> result = bookController.
                getBookById(bookId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.OK,
                result.getStatusCode());
        Assertions.assertEquals(bookId, result.getBody().id());
        Assertions.assertEquals(BookTestData.TITLE,
                result.getBody().title());
        Mockito.verify(bookService).getBookById(bookId);
    }

    @Test
    void methodShouldGetBookByTitleTest() {
        BookResponseDto response = BookTestData.getBookResponseDto();

        Mockito.when(bookService.getBookByTitle(BookTestData.TITLE))
                .thenReturn(response);

        ResponseEntity<BookResponseDto> result = bookController.
                getBookByTitle(BookTestData.TITLE);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.OK,
                result.getStatusCode());
        Assertions.assertEquals(BookTestData.TITLE,
                result.getBody().title());
        Mockito.verify(bookService).getBookByTitle(BookTestData.TITLE);
    }

    @Test
    void methodShouldGetAllBooksTest() {
        List<BookResponseDto> response = List.of(BookTestData.getBookResponseDto());

        Mockito.when(bookService.getAllBooks())
                .thenReturn(response);

        ResponseEntity<List<BookResponseDto>> result = bookController.
                getAllBooks();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.OK,
                result.getStatusCode());
        Assertions.assertEquals(1, result.getBody().size());
        Assertions.assertEquals(BookTestData.TITLE,
                result.getBody().get(0).title());
        Mockito.verify(bookService).getAllBooks();
    }

    @Test
    void methodShouldUpdateBookTest() {
        BookRequestDto request = BookTestData.getBookRequestDto();
        BookResponseDto response = BookTestData.getBookResponseDto();
        UUID bookId = BookTestData.ID;

        Mockito.when(bookService.updateBook(bookId, request))
                .thenReturn(response);

        ResponseEntity<BookResponseDto> result = bookController.
                updateBook(bookId, request);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.OK,
                result.getStatusCode());
        Assertions.assertEquals(response.id(),
                result.getBody().id());
        Mockito.verify(bookService).updateBook(bookId, request);
    }

    @Test
    void methodShouldDeleteBookTest() {
        UUID bookId = BookTestData.ID;

        ResponseEntity<Void> result = bookController.deleteBook(bookId);

        Assertions.assertEquals(HttpStatus.NO_CONTENT,
                result.getStatusCode());
        Mockito.verify(bookService).deleteBook(bookId);
    }
}