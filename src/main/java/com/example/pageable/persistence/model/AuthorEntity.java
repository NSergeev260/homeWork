package com.example.pageable.persistence.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "authors")
public class AuthorEntity {

    @Id
    @GeneratedValue
    @Column(name = "author_id")
    private UUID authorId;

    @Column(name = "author_name", nullable = false)
    private String authorName;

    @Column(name = "author_surname", nullable = false)
    private String authorSurname;

    @OneToMany(mappedBy = "authorEntity", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<BookEntity> bookEntityList = new ArrayList<>();
}
