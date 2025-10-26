package com.example.pageable.usercesses.dto;

import com.example.pageable.api.view.Views;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder(setterPrefix = "with")
public record AuthorResponseDto(

        @JsonView({Views.AuthorPublic.class, Views.AuthorDetails.class,
                Views.AuthorWithBooks.class})
        UUID authorId,

        @JsonView({Views.AuthorPublic.class, Views.AuthorDetails.class,
                Views.AuthorWithBooks.class})
        String authorName,

        @JsonView({Views.AuthorPublic.class, Views.AuthorDetails.class,
                Views.AuthorWithBooks.class})
        String authorSurname,

        @JsonView({Views.AuthorDetails.class, Views.AuthorWithBooks.class})
        @JsonBackReference("book-author")
        List<BookResponseDto> bookResponseDtoList
) {
}
