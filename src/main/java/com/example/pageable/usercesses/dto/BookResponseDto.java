package com.example.pageable.usercesses.dto;

import com.example.pageable.api.view.Views;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder(setterPrefix = "with")
public record BookResponseDto(

        @JsonView({Views.BookPublic.class, Views.BookDetails.class,
                Views.BookWithAuthor.class,})
        UUID bookId,

        @JsonView({Views.BookPublic.class, Views.BookDetails.class,
                Views.BookWithAuthor.class,})
        String bookTitle,

        @JsonView({Views.BookPublic.class, Views.BookDetails.class,
                Views.BookWithAuthor.class,})
        Long sizeInPages,

        @JsonView({Views.BookPublic.class, Views.BookDetails.class,
                Views.BookWithAuthor.class,})
        LocalDate dateOfPublishing,

        @JsonView({Views.BookDetails.class, Views.BookWithAuthor.class})
        @JsonManagedReference("book-author")
        AuthorResponseDto AuthorResponseDto
) {
}
