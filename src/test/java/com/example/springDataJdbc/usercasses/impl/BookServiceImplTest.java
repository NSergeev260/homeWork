package com.example.springDataJdbc.usercasses.impl;

import com.example.springDataJdbc.persistence.model.BookData;
import com.example.springDataJdbc.persistence.repository.BookRepository;
import com.example.springDataJdbc.usercasses.dto.BookRequestDto;
import com.example.springDataJdbc.usercasses.dto.BookResponseDto;
import com.example.springDataJdbc.usercasses.mapper.BookMapper;
import com.example.springDataJdbc.util.BookTestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private BookRepository bookRepo;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    void methodShouldAddBookTest() {
        BookRequestDto requestDto = BookTestData.getBookRequestDto();
        BookData bookData = BookTestData.getBookData();
        BookResponseDto responseDto = BookTestData.getBookResponseDto();

        Mockito.when(bookMapper.fromDtoToData(requestDto))
                .thenReturn(bookData);
        Mockito.when(bookRepo.insertBook(bookData))
                .thenReturn(bookData);
        Mockito.when(bookMapper.fromDataToDto(bookData))
                .thenReturn(responseDto);

        BookResponseDto result = bookService.insertBook(requestDto);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(BookTestData.TITLE, result.title());
        Mockito.verify(bookRepo).insertBook(bookData);
    }

    @Test
    void methodShouldGetBookByIdTest() {
        UUID bookId = BookTestData.ID;
        BookData bookData = BookTestData.getBookData();
        BookResponseDto responseDto = BookTestData.getBookResponseDto();

        Mockito.when(bookRepo.findBookById(bookId))
                .thenReturn(Optional.of(bookData));
        Mockito.when(bookMapper.fromDataToDto(bookData))
                .thenReturn(responseDto);

        BookResponseDto result = bookService.getBookById(bookId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(bookId, result.id());
        Assertions.assertEquals(BookTestData.TITLE, result.title());
    }

    @Test
    void methodShouldGetBookByTitleTest() {
        String title = BookTestData.TITLE;
        BookData bookData = BookTestData.getBookData();
        BookResponseDto responseDto = BookTestData.getBookResponseDto();

        Mockito.when(bookRepo.findBookByTitle(title))
                .thenReturn(Optional.of(bookData));
        Mockito.when(bookMapper.fromDataToDto(bookData))
                .thenReturn(responseDto);

        BookResponseDto result = bookService.getBookByTitle(title);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(title, result.title());
        Mockito.verify(bookRepo).findBookByTitle(title);
    }

    @Test
    void methodShouldGetAllBooksTest() {
        BookData bookData = BookTestData.getBookData();
        BookResponseDto responseDto = BookTestData.getBookResponseDto();
        List<BookData> bookList = List.of(bookData);

        Mockito.when(bookRepo.findAllBooks())
                .thenReturn(bookList);
        Mockito.when(bookMapper.fromDataToDto(bookData))
                .thenReturn(responseDto);

        List<BookResponseDto> result = bookService.getAllBooks();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(BookTestData.TITLE, result.get(0).title());
        Mockito.verify(bookRepo).findAllBooks();
    }

    @Test
    void methodShouldUpdateBookTest() {
        UUID bookId = BookTestData.ID;
        BookRequestDto requestDto = BookTestData.getBookRequestDto();
        BookData existingBook = BookTestData.getBookData();
        BookResponseDto responseDto = BookTestData.getBookResponseDto();

        Mockito.when(bookRepo.findBookById(bookId))
                .thenReturn(Optional.of(existingBook));
        Mockito.when(bookRepo.updateBook(existingBook))
                .thenReturn(existingBook);
        Mockito.when(bookMapper.fromDataToDto(existingBook))
                .thenReturn(responseDto);

        BookResponseDto result = bookService.updateBook(bookId, requestDto);

        Assertions.assertNotNull(result);
        Mockito.verify(bookRepo).updateBook(existingBook);
    }

    @Test
    void methodShouldDeleteBookTest() {
        UUID bookId = BookTestData.ID;
        BookData bookData = BookTestData.getBookData();

        Mockito.when(bookRepo.findBookById(bookId))
                .thenReturn(Optional.of(bookData));

        bookService.deleteBook(bookId);

        Mockito.verify(bookRepo).deleteBookById(bookId);
    }
}