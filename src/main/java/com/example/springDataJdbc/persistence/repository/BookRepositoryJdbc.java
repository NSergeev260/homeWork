package com.example.springDataJdbc.persistence.repository;

import com.example.springDataJdbc.persistence.model.BookData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Repository
@AllArgsConstructor
public class BookRepositoryJdbc implements BookRepository {

    private static final String INSERT_BOOK = "INSERT INTO books(id, title, author, publication_year) VALUES (?, ?, ?, ?)";
    private static final String GET_BOOK_BY_ID = "SELECT * FROM books WHERE id = ?";
    private static final String GET_BOOK_BY_TITLE = "SELECT * FROM books WHERE title = ?";
    private static final String GET_ALL_BOOKS = "SELECT * FROM books";
    private static final String UPDATE_BOOK = "UPDATE books SET title = ?, author = ?, publication_year = ? WHERE id = ?";
    private static final String DELETE_BOOK = "DELETE FROM books WHERE id = ?";

    private final JdbcTemplate jdbcTemplate;
    private final BookRowMapper bookRowMapper;

    @Override
    public BookData insertBook(BookData book) {
        UUID newId = UUID.randomUUID();
        jdbcTemplate.update(
                INSERT_BOOK, newId, book.getTitle(),
                book.getAuthor(), book.getPublicationYear());
        book.setId(newId);
        return book;
    }

    @Override
    public Optional<BookData> findBookById(UUID id) {
        try {
            BookData bookData = jdbcTemplate.queryForObject(
                    GET_BOOK_BY_ID, bookRowMapper, id);
            return Optional.ofNullable(bookData);
        } catch (EmptyResultDataAccessException e) {
            log.debug("Book not found. FAIL! id: {}", id);
            return Optional.empty();
        }
    }

    @Override
    public Optional<BookData> findBookByTitle(String title) {
        try {
            BookData bookData = jdbcTemplate.queryForObject(
                    GET_BOOK_BY_TITLE, bookRowMapper, title);
            return Optional.ofNullable(bookData);
        } catch (EmptyResultDataAccessException e) {
            log.debug("Book not found. FAIL! title: {}", title);
            return Optional.empty();
        }
    }

    @Override
    public List<BookData> findAllBooks() {
        return jdbcTemplate.query(GET_ALL_BOOKS, bookRowMapper);
    }

    @Override
    public BookData updateBook(BookData book) {
        jdbcTemplate.update(UPDATE_BOOK, book.getTitle(),
                book.getAuthor(), book.getPublicationYear(), book.getId());
        return book;
    }

    @Override
    public void deleteBookById(UUID id) {
        jdbcTemplate.update(DELETE_BOOK, id);
    }
}
