package com.example.objectMapper.persistence.repository;

import com.example.objectMapper.persistence.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

    Optional<ProductEntity> findByName(String nameOfProduct);
    List<ProductEntity> findByNameIn(List<String> names);
}
