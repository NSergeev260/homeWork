package com.example.objectMapper.persistence.repository;

import com.example.objectMapper.persistence.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
}
