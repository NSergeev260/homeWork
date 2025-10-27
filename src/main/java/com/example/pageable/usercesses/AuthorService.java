package com.example.pageable.usercesses;

import com.example.pageable.usercesses.dto.AuthorRequestDto;
import com.example.pageable.usercesses.dto.AuthorResponseDto;

import java.util.List;
import java.util.UUID;

public interface AuthorService {
    AuthorResponseDto addAuthor(AuthorRequestDto authorRequestDto);

    AuthorResponseDto getAuthorById(UUID authorId);

    List<AuthorResponseDto> getAllAuthors();

    AuthorResponseDto updateAuthor(UUID authorId, AuthorRequestDto authorRequestDto);

    void deleteAuthor(UUID authorId);
}
