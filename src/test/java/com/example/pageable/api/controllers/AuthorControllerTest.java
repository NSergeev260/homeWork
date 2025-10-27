package com.example.pageable.api.controllers;

import com.example.pageable.api.util.AuthorTestData;
import com.example.pageable.usercesses.AuthorService;
import com.example.pageable.usercesses.dto.AuthorRequestDto;
import com.example.pageable.usercesses.dto.AuthorResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class AuthorControllerTest {

    @Mock
    private AuthorService authorService;

    @InjectMocks
    private AuthorController authorController;

    @Test
    void methodShouldAddAuthorTest() {
        AuthorRequestDto request = AuthorTestData.getAuthorRequestDto();
        AuthorResponseDto response = AuthorTestData.getAuthorResponseDto();

        Mockito.when(authorService.addAuthor(request)).thenReturn(response);

        AuthorResponseDto result = authorController.addAuthor(request);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(response.authorId(), result.authorId());
        Assertions.assertEquals(response.authorName(), result.authorName());
        Assertions.assertEquals(response.authorSurname(), result.authorSurname());
        Mockito.verify(authorService).addAuthor(request);
    }

    @Test
    void methodShouldGetAuthorByIdTest() {
        AuthorResponseDto response = AuthorTestData.getAuthorResponseDto();
        UUID authorId = AuthorTestData.AUTHOR_ID;

        Mockito.when(authorService.getAuthorById(authorId)).thenReturn(response);

        AuthorResponseDto result = authorController.getAuthorById(authorId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(authorId, result.authorId());
        Assertions.assertEquals(AuthorTestData.NAME, result.authorName());
        Assertions.assertEquals(AuthorTestData.SURNAME, result.authorSurname());
        Mockito.verify(authorService).getAuthorById(authorId);
    }

    @Test
    void methodShouldGetAllAuthorsTest() {
        AuthorResponseDto authorResponse = AuthorTestData.getAuthorResponseDto();
        List<AuthorResponseDto> authors = List.of(authorResponse);

        Mockito.when(authorService.getAllAuthors()).thenReturn(authors);

        List<AuthorResponseDto> result = authorController.getAllAuthors();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(authorResponse.authorId(), result.get(0).authorId());
        Assertions.assertEquals(AuthorTestData.NAME, result.get(0).authorName());
        Mockito.verify(authorService).getAllAuthors();
    }

    @Test
    void methodShouldUpdateAuthorTest() {
        AuthorRequestDto request = AuthorTestData.getAuthorRequestDto();
        AuthorResponseDto response = AuthorTestData.getAuthorResponseDto();
        UUID authorId = AuthorTestData.AUTHOR_ID;

        Mockito.when(authorService.updateAuthor(authorId, request)).thenReturn(response);

        AuthorResponseDto result = authorController.updateAuthor(authorId, request);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(response.authorId(), result. authorId());
        Assertions.assertEquals(response.authorName(), result.authorName());
        Mockito.verify(authorService).updateAuthor(authorId, request);
    }

    @Test
    void methodShouldDeleteAuthorTest() {
        UUID authorId = AuthorTestData.AUTHOR_ID;

        authorController.deleteAuthor(authorId);

        Mockito.verify(authorService).deleteAuthor(authorId);
    }
}