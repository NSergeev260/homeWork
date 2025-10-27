package com.example.pageable.api.controllers;

import com.example.pageable.usercesses.AuthorService;
import com.example.pageable.usercesses.dto.AuthorRequestDto;
import com.example.pageable.usercesses.dto.AuthorResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/authors")
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    public AuthorResponseDto addAuthor(AuthorRequestDto authorRequestDto) {
        return authorService.addAuthor(authorRequestDto);
    }

    @GetMapping
    public AuthorResponseDto getAuthorById(UUID authorId){
        return authorService.getAuthorById(authorId);
    }

    @GetMapping
    public List<AuthorResponseDto> getAllAuthors(){
        return authorService.getAllAuthors();
    }


    @PutMapping
    public AuthorResponseDto updateAuthor(UUID authorId, AuthorRequestDto authorRequestDto){
        return authorService.updateAuthor(authorId, authorRequestDto);
    }

    @DeleteMapping
    public void deleteAuthor(UUID authorId){
        authorService.deleteAuthor(authorId);
    }

}
