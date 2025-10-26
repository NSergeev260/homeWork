package com.example.pageable.persistence.repository;

import com.example.pageable.persistence.model.AuthorEntity;
import com.example.pageable.persistence.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, UUID> {
    Optional<AuthorEntity> findByAuthorNameAndAuthorSurname(
            String authorName,
            String authorSurname
    );
}
