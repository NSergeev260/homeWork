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

        return bookMapper.fromDataToDto(savedBook);
    }

    @Transactional(readOnly = true)
    @Override
    public BookResponseDto getBookByID(UUID id) {
        BookData bookData = getByID(id);
        BookResponseDto bookResponseDto = bookMapper.fromDataToDto(bookData);
        return bookResponseDto;
    }

    @Transactional(readOnly = true)
    @Override
    public BookResponseDto getBookByTitle(String title) {
        BookData bookData = bookRepo.findBookByTitle(title)
                .orElseThrow(() -> new RuntimeException("Book not found. FAIL! title: " + title));
        BookResponseDto bookResponseDto = bookMapper.fromDataToDto(bookData);

        log.info("The book with the title {} FOUND. Time: {}"
                , title, LocalDateTime.now());

        return bookResponseDto;
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
        BookData bookToUpdate = bookMapper.fromDtoToData(bookRequestDto);

        bookData.setTitle(bookRequestDto.title());
        bookData.setAuthor(bookRequestDto.author());
        bookData.setPublicationYear(bookRequestDto.publicationYear());
        BookData updatedBook = bookRepo.updateBook()
        return null;
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
