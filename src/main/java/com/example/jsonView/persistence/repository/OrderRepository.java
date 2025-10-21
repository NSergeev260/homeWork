package com.example.jsonView.persistence.repository;

import com.example.jsonView.persistence.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
    List<OrderEntity> findByUserUserId(UUID userId);
}
