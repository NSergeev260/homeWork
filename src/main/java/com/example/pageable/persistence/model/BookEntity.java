package com.example.pageable.persistence.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "books")
public class BookEntity {

    @Id
    @GeneratedValue
    @Column(name = "book_id")
    private UUID bookId;

    @Column(name = "book_title", nullable = false)
    private String bookTitle;

    @Column(name = "size_in_pages")
    private Long sizeInPages;

    @Column(name = "year_of_publishing")
    private Integer yearOfPublishing;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private AuthorEntity authorEntity;
}
