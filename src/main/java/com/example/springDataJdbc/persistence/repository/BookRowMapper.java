package com.example.springDataJdbc.persistence.repository;

import com.example.springDataJdbc.persistence.model.BookData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class BookRowMapper implements RowMapper<BookData> {

    @Override
    public BookData mapRow(ResultSet rs, int rowNum) throws SQLException {
        BookData bookData = new BookData();
        bookData.setId(UUID.fromString(rs.getString("id")));
        bookData.setTitle(rs.getString("title"));
        bookData.setAuthor(rs.getString("author"));
        bookData.setPublicationYear(rs.getInt("publication_year"));
        return bookData;
    }
}
