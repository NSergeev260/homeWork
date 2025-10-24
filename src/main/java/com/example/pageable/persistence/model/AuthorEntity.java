package com.example.pageable.persistence.model;

import com.example.pageable.usercesses.dto.BookResponseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "authors")
public class AuthorEntity {

    @Id
    @GeneratedValue
    @Column(name = "authorId")
    UUID authorId;

    @Column(name = "author_name")
    String authorName;

    @Column(name = "author_surname")
    String authorSurname;

    @Column(name = "author_book_list")
    List<BookResponseDto> bookResponseDtoList;
}
