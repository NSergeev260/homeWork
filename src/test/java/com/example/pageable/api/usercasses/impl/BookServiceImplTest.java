package com.example.pageable.api.usercasses.impl;

import com.example.pageable.persistence.model.AuthorEntity;
import com.example.pageable.persistence.model.BookEntity;
import com.example.pageable.persistence.repository.AuthorRepository;
import com.example.pageable.persistence.repository.BookRepository;
import com.example.pageable.usercesses.dto.BookRequestDto;
import com.example.pageable.usercesses.dto.BookResponseDto;
import com.example.pageable.usercesses.impl.BookServiceImpl;
import com.example.pageable.usercesses.mapper.BookMapper;
import com.example.pageable.api.util.AuthorTestData;
import com.example.pageable.api.util.BookTestData;
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
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private BookRepository bookRepo;

    @Mock
    private AuthorRepository authorRepo;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookServiceImpl bookService;

    private static final String TITLE = "История";

    @Test
    void methodShouldAddBookTest() {
        BookRequestDto requestDto = BookTestData.getBookRequestDto();
        AuthorEntity authorEntity = AuthorTestData.getAuthorEntity();
        BookEntity bookEntity = BookTestData.getBookEntity(authorEntity);
        BookResponseDto responseDto = BookTestData.getBookResponseDto();

        Mockito.when(bookRepo.findByBookTitleAndAuthorEntityAuthorIdAndYearOfPublishing(
                BookTestData.BOOK_TITLE, BookTestData.AUTHOR_ID,
                        BookTestData.YEAR_OF_PUBLISHING))
                .thenReturn(Optional.empty());
        Mockito.when(authorRepo.findById(BookTestData.AUTHOR_ID))
                .thenReturn(Optional.of(authorEntity));
        Mockito.when(bookMapper.fromDtoToEntity(requestDto))
                .thenReturn(bookEntity);
        Mockito.when(bookRepo.save(bookEntity))
                .thenReturn(bookEntity);
        Mockito.when(bookMapper.fromEntityToDto(bookEntity))
                .thenReturn(responseDto);

        BookResponseDto result = bookService.addBook(requestDto);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(BookTestData.BOOK_TITLE,
                result.bookTitle());
        Mockito.verify(bookRepo, times(1))
                .save(bookEntity);
    }

    @Test
    void methodShouldGetBookByIdTest() {
        UUID bookId = BookTestData.BOOK_ID;
        BookEntity bookEntity = BookTestData.getBookEntity(AuthorTestData.getAuthorEntity());
        BookResponseDto responseDto = BookTestData.getBookResponseDto();

        Mockito.when(bookRepo.findById(bookId))
                .thenReturn(Optional.of(bookEntity));
        Mockito.when(bookMapper.fromEntityToDto(bookEntity))
                .thenReturn(responseDto);

        BookResponseDto result = bookService.getBookById(bookId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(bookId, result.bookId());
        Assertions.assertEquals(BookTestData.BOOK_TITLE,
                result.bookTitle());
    }

    @Test
    void methodShouldGetBookByTitleTest() {
        String title = TITLE;
        Pageable pageable = PageRequest.of(0, 10);
        BookEntity bookEntity = BookTestData.getBookEntity(AuthorTestData.getAuthorEntity());
        Page<BookEntity> bookPage = new PageImpl<>(List.of(bookEntity));
        BookResponseDto responseDto = BookTestData.getBookResponseDto();

        Mockito.when(bookRepo.findByBookTitleContainingIgnoreCase(title, pageable))
                .thenReturn(bookPage);
        Mockito.when(bookMapper.fromEntityToDto(bookEntity))
                .thenReturn(responseDto);

        Page<BookResponseDto> result = bookService.getBookByTitle(title, pageable);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.getTotalElements());
        Mockito.verify(bookRepo, times(1))
                .findByBookTitleContainingIgnoreCase(title, pageable);
    }

    @Test
    void methodShouldGetAllBooksTest() {
        Pageable pageable = PageRequest.of(0, 10);
        BookEntity bookEntity = BookTestData.getBookEntity(AuthorTestData.getAuthorEntity());
        Page<BookEntity> bookPage = new PageImpl<>(List.of(bookEntity));
        BookResponseDto responseDto = BookTestData.getBookResponseDto();

        Mockito.when(bookRepo.findAll(pageable))
                .thenReturn(bookPage);
        Mockito.when(bookMapper.fromEntityToDto(bookEntity))
                .thenReturn(responseDto);

        Page<BookResponseDto> result = bookService.getAllBooks(pageable);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.getTotalElements());
        Mockito.verify(bookRepo, times(1)).findAll(pageable);
    }

    @Test
    void methodShouldGetBooksByAuthorTest() {
        UUID authorId = AuthorTestData.AUTHOR_ID;
        Pageable pageable = PageRequest.of(0, 10);
        BookEntity bookEntity = BookTestData.getBookEntity(AuthorTestData.getAuthorEntity());
        Page<BookEntity> bookPage = new PageImpl<>(List.of(bookEntity));
        BookResponseDto responseDto = BookTestData.getBookResponseDto();

        Mockito.when(bookRepo.findByAuthorId(authorId, pageable))
                .thenReturn(bookPage);
        Mockito.when(bookMapper.fromEntityToDto(bookEntity))
                .thenReturn(responseDto);

        Page<BookResponseDto> result = bookService.getBooksByAuthor(authorId, pageable);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.getTotalElements());
        Mockito.verify(bookRepo, times(1))
                .findByAuthorId(authorId, pageable);
    }

    @Test
    void methodShouldUpdateBookTest() {
        UUID bookId = BookTestData.BOOK_ID;
        BookRequestDto requestDto = BookTestData.getBookRequestDto();
        BookEntity existingBook = BookTestData.getBookEntity(AuthorTestData.getAuthorEntity());
        AuthorEntity authorEntity = AuthorTestData.getAuthorEntity();
        BookResponseDto bookResponseDto = BookTestData.getBookResponseDto();

        Mockito.when(bookRepo.findById(bookId))
                .thenReturn(Optional.of(existingBook));
        Mockito.when(authorRepo.findById(BookTestData.AUTHOR_ID))
                .thenReturn(Optional.of(authorEntity));
        Mockito.when(bookRepo.save(existingBook))
                .thenReturn(existingBook);
        Mockito.when(bookMapper.fromEntityToDto(existingBook))
                .thenReturn(bookResponseDto);

        BookResponseDto result = bookService.updateBook(bookId, requestDto);

        Assertions.assertNotNull(result);
        Mockito.verify(bookRepo, times(1))
                .save(existingBook);
    }

    @Test
    void methodShouldDeleteBookTest() {
        UUID bookId = BookTestData.BOOK_ID;
        BookEntity bookEntity = BookTestData.getBookEntity(AuthorTestData.getAuthorEntity());

        Mockito.when(bookRepo.findById(bookId))
                .thenReturn(Optional.of(bookEntity));

        bookService.deleteBook(bookId);

        Mockito.verify(bookRepo, times(1))
                .delete(bookEntity);
    }
}