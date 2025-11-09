package com.example.springDataJdbc.persistence.repository;

import com.example.springDataJdbc.persistence.model.BookData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Repository
@AllArgsConstructor
public class BookRepositoryJdbc implements BookRepository {

    private static final String INSERT_BOOK =
            "INSERT INTO books(id, title, author, publication_year) " +
                    "VALUES (:id, :title, :author, :publicationYear)";

    private static final String GET_BOOK_BY_ID =
            "SELECT * FROM books WHERE id = :id";

    private static final String GET_BOOK_BY_TITLE =
            "SELECT * FROM books WHERE title = :title";

    private static final String GET_ALL_BOOKS =
            "SELECT * FROM books";

    private static final String UPDATE_BOOK =
            "UPDATE books SET title = :title, author = :author, " +
                    "publication_year = :publicationYear WHERE id = :id";

    private static final String DELETE_BOOK =
            "DELETE FROM books WHERE id = :id";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final BookRowMapper bookRowMapper;

    @Override
    public BookData insertBook(BookData book) {
        UUID newId = UUID.randomUUID();
        SqlParameterSource insertParameters = new MapSqlParameterSource()
                .addValue("id", newId)
                .addValue("title", book.getTitle())
                .addValue("author", book.getAuthor())
                .addValue("publicationYear", book.getPublicationYear());
        namedParameterJdbcTemplate.update(INSERT_BOOK, insertParameters);
        book.setId(newId);

        return book;
    }

    @Override
    public Optional<BookData> findBookById(UUID id) {
        try {
            SqlParameterSource findParameters = new MapSqlParameterSource()
                    .addValue("id", id);
            BookData bookData = namedParameterJdbcTemplate.queryForObject(
                    GET_BOOK_BY_ID, findParameters, bookRowMapper);

            return Optional.ofNullable(bookData);

        } catch (EmptyResultDataAccessException e) {
            log.debug("Book not found. FAIL! id: {}", id);

            return Optional.empty();
        }
    }

    @Override
    public Optional<BookData> findBookByTitle(String title) {
        try {
            SqlParameterSource findParameters = new MapSqlParameterSource()
                    .addValue("title", title);
            BookData bookData = namedParameterJdbcTemplate.queryForObject(
                    GET_BOOK_BY_TITLE, findParameters, bookRowMapper);

            return Optional.ofNullable(bookData);

        } catch (EmptyResultDataAccessException e) {
            log.debug("Book not found. FAIL! title: {}", title);

            return Optional.empty();
        }
    }

    @Override
    public List<BookData> findAllBooks() {

        return namedParameterJdbcTemplate.query(
                GET_ALL_BOOKS, new MapSqlParameterSource(), bookRowMapper);
    }

    @Override
    public BookData updateBook(BookData book) {
        SqlParameterSource updateParameters = new MapSqlParameterSource()
                .addValue("id", book.getId())
                .addValue("title", book.getTitle())
                .addValue("author", book.getAuthor())
                .addValue("publicationYear", book.getPublicationYear());
        namedParameterJdbcTemplate.update(UPDATE_BOOK, updateParameters);

        return book;
    }

    @Override
    public void deleteBookById(UUID id) {
        SqlParameterSource deleteParameters = new MapSqlParameterSource()
                .addValue("id", id);
        namedParameterJdbcTemplate.update(DELETE_BOOK, deleteParameters);
    }
}
