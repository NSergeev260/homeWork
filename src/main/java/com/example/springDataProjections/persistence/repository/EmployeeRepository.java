package com.example.springDataProjections.persistence.repository;

import com.example.springDataProjections.persistence.model.EmployeeEntity;
import com.example.springDataProjections.persistence.projection.EmployeeProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, UUID> {

    List<EmployeeProjection> findBy();

    List<EmployeeProjection> getFullName();

    List<EmployeeProjection> getPosition();

    List<EmployeeProjection> getDepartmentName();
}
