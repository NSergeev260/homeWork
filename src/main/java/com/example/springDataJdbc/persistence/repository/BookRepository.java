package com.example.springDataJdbc.persistence.repository;


import com.example.springDataJdbc.persistence.model.BookData;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookRepository {

    BookData insertBook(BookData book);

    Optional<BookData> findBookById(UUID id);

    Optional<BookData> findBookByTitle(String title);

    List<BookData> findAllBooks();

    BookData updateBook(BookData book);

    void deleteBookById(UUID id);
}
