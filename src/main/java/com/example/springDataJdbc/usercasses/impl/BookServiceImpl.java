package com.example.springDataJdbc.usercasses.impl;

import com.example.springDataJdbc.persistence.model.BookData;
import com.example.springDataJdbc.persistence.repository.BookRepository;
import com.example.springDataJdbc.usercasses.BookService;
import com.example.springDataJdbc.usercasses.dto.BookRequestDto;
import com.example.springDataJdbc.usercasses.dto.BookResponseDto;
import com.example.springDataJdbc.usercasses.mapper.BookMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepo;
    private final BookMapper bookMapper;

    @Transactional
    @Override
    public BookResponseDto insertBook(BookRequestDto bookRequestDto) {
        BookData bookData = bookMapper.fromDtoToData(bookRequestDto);
        BookData savedBook = bookRepo.insertBook(bookData);

        log.info("Book with id {} has been ADDED. Time: {}"
                , savedBook.getId(), LocalDateTime.now());

        return bookMapper.fromDataToDto(savedBook);
    }

    @Transactional(readOnly = true)
    @Override
    public BookResponseDto getBookById(UUID id) {
        BookData bookData = getByID(id);

        log.info("Book with id {} FOUND. Time: {}"
                , id, LocalDateTime.now());

        return bookMapper.fromDataToDto(bookData);
    }

    @Transactional(readOnly = true)
    @Override
    public BookResponseDto getBookByTitle(String title) {
        BookData bookData = bookRepo.findBookByTitle(title)
                .orElseThrow(() -> new RuntimeException("Book not found. FAIL! title: " + title));

        log.info("The book with the title {} FOUND. Time: {}"
                , title, LocalDateTime.now());

        return bookMapper.fromDataToDto(bookData);
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookResponseDto> getAllBooks() {

        log.info("Method `getAllBooks` was run. Date {}", LocalDateTime.now());

        return bookRepo.findAllBooks().stream()
                .map(bookMapper::fromDataToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public BookResponseDto updateBook(UUID id, BookRequestDto bookRequestDto) {
        BookData bookData = getByID(id);
        bookData.setTitle(bookRequestDto.title());
        bookData.setAuthor(bookRequestDto.author());
        bookData.setPublicationYear(bookRequestDto.publicationYear());
        BookData updatedBook = bookRepo.updateBook(bookData);

        log.info("The book with the id {} has been UPDATED, Date {}",
                updatedBook.getId(), LocalDateTime.now());

        return bookMapper.fromDataToDto(updatedBook);
    }

    @Transactional
    @Override
    public void deleteBook(UUID id) {
        getByID(id);
        bookRepo.deleteBookById(id);

        log.info("The book with the id {} has been DELETED, Date {}"
                , id, LocalDateTime.now());
    }

    private BookData getByID(UUID id) {

        return bookRepo.findBookById(id)
                .orElseThrow(() -> new RuntimeException("Book not found. FAIL! id: " + id));
    }
}
