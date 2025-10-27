package com.example.pageable.api.controllers;

import com.example.pageable.api.util.BookTestData;
import com.example.pageable.usercesses.BookService;
import com.example.pageable.usercesses.dto.BookRequestDto;
import com.example.pageable.usercesses.dto.BookResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    private final Pageable PAGEABLE = PageRequest.of(0, 10);

    @Test
    void methodShouldAddBookTest() {
        BookRequestDto request = BookTestData.getBookRequestDto();
        BookResponseDto response = BookTestData.getBookResponseDto();

        Mockito.when(bookService.addBook(request)).thenReturn(response);

        BookResponseDto result = bookController.addBook(request);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(response.bookId(), result.bookId());
        Assertions.assertEquals(response.bookTitle(), result.bookTitle());
        Assertions.assertEquals(response.sizeInPages(), result.sizeInPages());
        Mockito.verify(bookService).addBook(request);
    }

    @Test
    void methodShouldGetBookByIdTest() {
        BookResponseDto response = BookTestData.getBookResponseDto();
        UUID bookId = BookTestData.BOOK_ID;

        Mockito.when(bookService.getBookById(bookId)).thenReturn(response);

        BookResponseDto result = bookController.getBookById(bookId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(bookId, result.bookId());
        Assertions.assertEquals(BookTestData.BOOK_TITLE, result.bookTitle());
        Assertions.assertEquals(BookTestData.SIZE_IN_PAGES, result.sizeInPages());
        Mockito.verify(bookService).getBookById(bookId);
    }

    @Test
    void methodShouldGetBookByTitleTest() {
        BookResponseDto bookResponse = BookTestData.getBookResponseDto();
        Page<BookResponseDto> page = new PageImpl<>(List.of(bookResponse));

        Mockito.when(bookService.getBookByTitle(BookTestData.BOOK_TITLE, PAGEABLE)).thenReturn(page);

        Page<BookResponseDto> result = bookController.getBookByTitle(BookTestData.BOOK_TITLE, PAGEABLE);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.getContent().size());
        Assertions.assertEquals(bookResponse.bookId(), result.getContent().get(0).bookId());
        Assertions.assertEquals(BookTestData.BOOK_TITLE, result.getContent().get(0).bookTitle());
        Mockito.verify(bookService).getBookByTitle(BookTestData.BOOK_TITLE, PAGEABLE);
    }

    @Test
    void methodShouldGetBooksByAuthorTest() {
        BookResponseDto bookResponse = BookTestData.getBookResponseDto();
        Page<BookResponseDto> page = new PageImpl<>(List.of(bookResponse));
        UUID authorId = BookTestData.AUTHOR_ID;

        Mockito.when(bookService.getBooksByAuthor(authorId, PAGEABLE)).thenReturn(page);

        Page<BookResponseDto> result = bookController.getBooksByAuthor(authorId, PAGEABLE);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.getContent().size());
        Assertions.assertEquals(authorId, result.getContent().get(0).AuthorResponseDto().authorId());
        Mockito.verify(bookService).getBooksByAuthor(authorId, PAGEABLE);
    }

    @Test
    void methodShouldGetAllBooksTest() {
        BookResponseDto bookResponse = BookTestData.getBookResponseDto();
        Page<BookResponseDto> page = new PageImpl<>(List.of(bookResponse));

        Mockito.when(bookService.getAllBooks(PAGEABLE)).thenReturn(page);

        Page<BookResponseDto> result = bookController.getAllBooks(PAGEABLE);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.getContent().size());
        Assertions.assertEquals(BookTestData.BOOK_TITLE, result.getContent().get(0).bookTitle());
        Mockito.verify(bookService).getAllBooks(PAGEABLE);
    }

    @Test
    void methodShouldUpdateBookTest() {
        BookRequestDto request = BookTestData.getBookRequestDto();
        BookResponseDto response = BookTestData.getBookResponseDto();
        UUID bookId = BookTestData.BOOK_ID;

        Mockito.when(bookService.updateBook(bookId, request)).thenReturn(response);

        BookResponseDto result = bookController.updateBook(bookId, request);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(response.bookId(), result.bookId());
        Assertions.assertEquals(response.bookTitle(), result.bookTitle());
        Mockito.verify(bookService).updateBook(bookId, request);
    }

    @Test
    void methodShouldDeleteBookTest() {
        UUID bookId = BookTestData.BOOK_ID;

        bookController.deleteBook(bookId);

        Mockito.verify(bookService).deleteBook(bookId);
    }
}