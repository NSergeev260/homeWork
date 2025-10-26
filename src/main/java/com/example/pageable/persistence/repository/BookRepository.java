package com.example.pageable.persistence.repository;

import com.example.pageable.persistence.model.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, UUID> {

    Optional<BookEntity> findByBookTitleAndAuthorEntityAuthorIdAndDateOfPublishing(
            String bookTitle,
            UUID authorId,
            LocalDate dateOfPublishing);

    @Query("SELECT a FROM BookEntity a WHERE a.authorEntity.authorId = :authorId")
    Page<BookEntity> findByAuthorId(@Param("authorId") UUID authorId, Pageable pageable);

    Page<BookEntity> findByBookTitleContainingIgnoreCase(String title, Pageable pageable);
}
