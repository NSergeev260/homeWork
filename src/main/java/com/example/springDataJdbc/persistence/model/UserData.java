package com.example.springDataJdbc.persistence.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Table("users")
public class UserData {

    @Id
    private UUID id;
    private String name;
    private String email;
    private String provider;
    @Column("provider_id")
    private String providerId;
    private UserRole role;
}
