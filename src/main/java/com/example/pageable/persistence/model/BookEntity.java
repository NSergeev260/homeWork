package com.example.pageable.persistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class BookEntity {

    @Id
    @GeneratedValue
    @Column(name = "book_id")
    UUID bookId;

    @Column(name = "book_name")
    String bookName;

    @Column(name = "size_in_pages")
    Long sizeInPages;

    @Column(name = "data_of_publishing")
    LocalDate dateOfPublishing;
}
