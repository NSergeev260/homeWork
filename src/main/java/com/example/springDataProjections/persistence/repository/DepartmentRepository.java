package com.example.springDataProjections.persistence.repository;

import com.example.springDataProjections.persistence.model.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, UUID> {
}
