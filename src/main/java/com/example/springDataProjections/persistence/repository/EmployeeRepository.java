package com.example.springDataProjections.persistence.repository;

import com.example.springDataProjections.persistence.model.EmployeeEntity;
import com.example.springDataProjections.persistence.projection.EmployeeProjection;
import com.example.springDataProjections.persistence.projection.EmployeeSalaryProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, UUID> {

    @Query("SELECT " +
            "CONCAT(e.lastName, ' ', e.firstName) as fullName, " +
            "e.position as position, " +
            "d.name as departmentName " +
            "FROM EmployeeEntity e " +
            "JOIN e.department d " +
            "WHERE e.id = :id")
    Optional<EmployeeProjection> findEmployeeProjectionById(@Param("id")UUID id);


    @Query("SELECT " +
            "CONCAT(e.lastName, ' ', e.firstName) as fullName, " +
            "d.name as departmentName, " +
            "e.salary as salary " +
            "FROM EmployeeEntity e " +
            "JOIN e.department d")
    List<EmployeeSalaryProjection> findEmployeeSalaryProjections();
}
