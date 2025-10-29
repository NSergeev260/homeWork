package com.example.objectMapper.persistence.repository;

import com.example.objectMapper.persistence.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
}
