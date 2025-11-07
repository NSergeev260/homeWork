package com.example.springDataProjections.persistence.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "departments")
public class DepartmentEntity {

    @Id
    @Column(name = "id")
    private UUID id = UUID.randomUUID();

    @Column(name = "name")
    private String name;
}
