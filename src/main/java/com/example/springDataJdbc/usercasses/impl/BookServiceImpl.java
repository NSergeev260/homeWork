package com.example.springDataJdbc.usercasses.impl;

import com.example.springDataJdbc.persistence.model.BookData;
import com.example.springDataJdbc.persistence.repository.BookRepository;
import com.example.springDataJdbc.usercasses.BookService;
import com.example.springDataJdbc.usercasses.dto.BookRequestDto;
import com.example.springDataJdbc.usercasses.dto.BookResponseDto;
import com.example.springDataJdbc.usercasses.mapper.BookMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookResponseDto insertBook(BookRequestDto bookRequestDto) {
        return null;
    }

    @Override
    public BookResponseDto getBookByID(UUID id) {
        return null;
    }

    @Override
    public BookResponseDto getBookByTitle(String title) {
        return null;
    }

    @Override
    public BookResponseDto updateBook(UUID id, BookRequestDto bookRequestDto) {
        return null;
    }

    @Transactional
    @Override
    public void deleteBook(UUID id) {
        BookData existingBook = bookRepository.findBookById(id)
                .orElseThrow(() -> new RuntimeException("Book not found. FAIL! id: " + id));
        bookRepository.deleteBookById(id);
        log.info("The book with the id {} has been DELETED, Date {}"
                , id, LocalDateTime.now());
    }
}
