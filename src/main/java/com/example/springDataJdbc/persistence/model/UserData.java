package com.example.springDataJdbc.persistence.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Entity
@Table(name = "users")
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @Getter
    @Setter
    private Long id;

    @Column(nullable = false)
    @Getter
    @Setter
    private String name;

    @Column(length = 60, nullable = false)
    @Setter
    @Getter
    private String email;

    @Column(nullable = false)
    @Getter
    @Setter
    private String provider;

    @Column(nullable = false)
    @Getter
    @Setter
    private String providerId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Getter
    @Setter
    private UserRole role;

}
