package com.example.pageable.usercesses;

import com.example.pageable.usercesses.dto.AuthorResponseDto;
import com.example.pageable.usercesses.dto.BookResponseDto;

import java.util.List;
import java.util.UUID;

public interface AuthorService {
    AuthorResponseDto addAuthor(AuthorResponseDto authorResponseDto);
    AuthorResponseDto getAuthor(UUID authorId);
    List<AuthorResponseDto> getAllAuthors();
    AuthorResponseDto updateAuthor(UUID authorId, BookResponseDto bookResponseDto);
    void deleteAuthor(UUID authorId);
}
