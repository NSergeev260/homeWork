package com.example.pageable.usercesses.impl;

import com.example.pageable.api.exeption.BadRequestException;
import com.example.pageable.api.exeption.NotFoundException;
import com.example.pageable.persistence.model.AuthorEntity;
import com.example.pageable.persistence.model.BookEntity;
import com.example.pageable.persistence.repository.AuthorRepository;
import com.example.pageable.persistence.repository.BookRepository;
import com.example.pageable.usercesses.BookService;
import com.example.pageable.usercesses.dto.BookRequestDto;
import com.example.pageable.usercesses.dto.BookResponseDto;
import com.example.pageable.usercesses.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookMapper bookMapper;
    private final BookRepository bookRepo;
    private final AuthorRepository authorRepo;

    @Transactional
    @Override
    public BookResponseDto addBook(BookRequestDto bookRequestDto) {

        Optional<BookEntity> bookExists =
                bookRepo.findByBookTitleAndAuthorEntityAuthorIdAndDateOfPublishing(
                        bookRequestDto.bookTitle(),
                        bookRequestDto.authorId(),
                        bookRequestDto.dateOfPublishing()
                );

        if (bookExists.isPresent()) {
            throw new BadRequestException("This book already exists. FAIL!");
        }

        AuthorEntity authorEntity = getAuthorEntity(bookRequestDto);

        BookEntity bookEntity = bookMapper.fromDtoToEntity(bookRequestDto);
        bookEntity.setAuthorEntity(authorEntity);
        BookEntity savedBookEntity = bookRepo.save(bookEntity);

        log.info("The book with the id {} has been ADDED. Time: {}",
                savedBookEntity.getBookId(), LocalDateTime.now());

        return bookMapper.fromEntityToDto(savedBookEntity);
    }

    @Transactional(readOnly = true)
    @Override
    public BookResponseDto getBookById(UUID bookId) {
        BookEntity bookEntity = getBookRepoByID(bookId);

        log.info("The book with the id {} FOUND. Time: {}"
                , bookId, LocalDateTime.now());

        return bookMapper.fromEntityToDto(bookEntity);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<BookResponseDto> getBookByTitle(String title, Pageable pageable) {
        log.info("Get a book by title: '{}'", title);

        return bookRepo.findByBookTitleContainingIgnoreCase(title, pageable)
                .map(bookMapper::fromEntityToDto);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<BookResponseDto> getAllBooks(Pageable pageable) {
        log.info("Get all books with pagination: page {}, size {}",
                pageable.getPageNumber(), pageable.getPageSize());

        return bookRepo.findAll(pageable)
                .map(bookMapper::fromEntityToDto);

    }

    @Transactional(readOnly = true)
    @Override
    public Page<BookResponseDto> getBooksByAuthor(UUID authorId, Pageable pageable) {
        log.info("Get books by authorId: {}", authorId);

        return bookRepo.findByAuthorId(authorId, pageable)
                .map(bookMapper::fromEntityToDto);
    }

    @Transactional
    @Override
    public BookResponseDto updateBook(UUID bookId, BookRequestDto bookRequestDto) {
        BookEntity bookEntity = getBookRepoByID(bookId);

        bookEntity.setBookTitle(bookRequestDto.bookTitle());
        bookEntity.setSizeInPages(bookRequestDto.sizeInPages());
        bookEntity.setDateOfPublishing(bookRequestDto.dateOfPublishing());

        AuthorEntity author = getAuthorEntity(bookRequestDto);

        bookEntity.setAuthorEntity(author);
        BookEntity updatedBookEntity = bookRepo.save(bookEntity);

        log.info("The book with the id {} has been UPDATED, Date {}"
                , bookId, LocalDateTime.now());

        return bookMapper.fromEntityToDto(updatedBookEntity);

    }

    @Transactional
    @Override
    public void deleteBook(UUID bookId) {
        BookEntity bookEntity = getBookRepoByID(bookId);
        bookRepo.delete(bookEntity);

        log.info("The book with the id {} has been DELETED, Date {}"
                , bookId, LocalDateTime.now());
    }

    private BookEntity getBookRepoByID(UUID bookId) {
        BookEntity bookEntity = bookRepo.findById(bookId)
                .orElseThrow(() ->
                        new NotFoundException("Book not found. FAIL! ID: " + bookId));
        return bookEntity;
    }

    private AuthorEntity getAuthorEntity(BookRequestDto bookRequestDto) {
        AuthorEntity authorEntity = authorRepo.findById(bookRequestDto.authorId())
                .orElseThrow(() ->
                        new NotFoundException("Author not found. FAIL! ID: "
                                + bookRequestDto.authorId()));

        return authorEntity;
    }
}
