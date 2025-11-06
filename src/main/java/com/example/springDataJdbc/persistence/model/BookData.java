package com.example.springDataJdbc.persistence.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Table("books")
public class BookData {

    @Id
    private UUID id;
    private String title;
    private String author;
    private Integer publicationYear;
}