package com.example.objectMapper.persistence.repository;

import com.example.objectMapper.persistence.model.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {
}
