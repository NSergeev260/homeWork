package com.example.pageable.usercesses.impl;

import com.example.pageable.api.exeption.BadRequestException;
import com.example.pageable.api.exeption.NotFoundException;
import com.example.pageable.persistence.model.AuthorEntity;
import com.example.pageable.persistence.repository.AuthorRepository;
import com.example.pageable.usercesses.AuthorService;
import com.example.pageable.usercesses.dto.AuthorRequestDto;
import com.example.pageable.usercesses.dto.AuthorResponseDto;
import com.example.pageable.usercesses.mapper.AuthorMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorMapper authorMapper;
    private final AuthorRepository authorRepo;


    @Transactional
    @Override
    public AuthorResponseDto addAuthor(AuthorRequestDto authorRequestDto) {
//        Optional<AuthorEntity> authorExists =
//                authorRepo.findByAuthorNameAndAuthorSurname(
//                        authorRequestDto.authorName(),
//                        authorRequestDto.authorSurname()
//                );
//
//        if (authorExists.isPresent()) {
//            throw new BadRequestException("This author already exists. FAIL!");
//        }
//
//        AuthorEntity authorEntity = authorMapper.fromDtoToEntity(authorRequestDto);
        AuthorEntity authorEntity = AuthorEntity.builder()
                .withAuthorName(authorRequestDto.authorName())      // Обязательное поле!
                .withAuthorSurname(authorRequestDto.authorSurname()) // Обязательное поле!
                .build();
        AuthorEntity savedAuthor = authorRepo.save(authorEntity);

        log.info("The author with the id {} has been ADDED. Time: {}",
                savedAuthor.getAuthorId(), LocalDateTime.now());

        return authorMapper.fromEntityToDto(savedAuthor);
    }

    @Transactional(readOnly = true)
    @Override
    public AuthorResponseDto getAuthorById(UUID authorId) {
        AuthorEntity authorEntity = getAuthorRepoByID(authorId);

        log.info("The author with the id {} FOUND. Time: {}", authorId, LocalDateTime.now());

        return authorMapper.fromEntityToDto(authorEntity);
    }

    @Transactional(readOnly = true)
    @Override
    public List<AuthorResponseDto> getAllAuthors() {

        log.info("Get all authors. Date {}", LocalDateTime.now());

        return authorRepo.findAll().stream()
                .map(authorMapper::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public AuthorResponseDto updateAuthor(UUID authorId, AuthorRequestDto authorRequestDto) {
        AuthorEntity authorEntity = getAuthorRepoByID(authorId);
        authorEntity.setAuthorName(authorRequestDto.authorName());
        authorEntity.setAuthorSurname(authorRequestDto.authorSurname());
        AuthorEntity updatedAuthor = authorRepo.save(authorEntity);

        log.info("The author with the id {} has been UPDATED, Date {}",
                updatedAuthor.getAuthorId(), LocalDateTime.now());

        return authorMapper.fromEntityToDto(updatedAuthor);
    }

    @Transactional
    @Override
    public void deleteAuthor(UUID authorId) {
        AuthorEntity author = getAuthorRepoByID(authorId);

        authorRepo.deleteById(authorId);

        log.info("The author with the id {} has been DELETED, Date {}", authorId, LocalDateTime.now());
    }

    private AuthorEntity getAuthorRepoByID(UUID authorId) {
        AuthorEntity authorEntity = authorRepo.findById(authorId)
                .orElseThrow(() ->
                        new NotFoundException("Author not found. FAIL! ID: " + authorId));

        return authorEntity;
    }
}
