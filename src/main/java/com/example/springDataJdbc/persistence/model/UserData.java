package com.example.springDataJdbc.persistence.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Table(name = "users")
public class UserData {

    @Id
    private UUID id;
    private String name;
    private String email;
    private String provider;
    private UUID providerId;
    private UserRole role;
}
