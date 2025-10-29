package com.example.objectMapper.persistence.model;

import jakarta.persistence.*;
import lombok.*;

import javax.annotation.processing.Generated;
import java.util.UUID;

@Builder(setterPrefix = "with")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class CustomerEntity {

    @Id
    @GeneratedValue
    @Column(name = "customer_id")
    UUID customerId;

    @Column(name = "first_name", nullable = false)
    String firstName;

    @Column(name = "last_name", nullable = false)
    String lastName;

    @Column(name = "email", nullable = false)
    String email;

    @Column(name = "contact_number", nullable = false)
    String contactNumber;
}
