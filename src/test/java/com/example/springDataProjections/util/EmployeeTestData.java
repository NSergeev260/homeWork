package com.example.springDataProjections.util;

import com.example.springDataProjections.persistence.model.DepartmentEntity;
import com.example.springDataProjections.persistence.model.EmployeeEntity;
import com.example.springDataProjections.usercasses.dto.EmployeeRequestDto;
import com.example.springDataProjections.usercasses.dto.EmployeeResponseDto;

import java.math.BigDecimal;
import java.util.UUID;

public class EmployeeTestData {

    public static final UUID EMPLOYEE_ID =
            UUID.fromString("a1b1c1d1-e1f1-4a1a-8a1a-a1b1c1d1e1f1");
    public static final UUID DEPARTMENT_ID =
            UUID.fromString("cdef0123-4567-4990-1234-567890123456");
    
    public static final String FIRST_NAME = "John";
    public static final String LAST_NAME = "Doe";
    public static final String POSITION = "CTO";
    public static final BigDecimal SALARY = new BigDecimal("450000.00");
    public static final String DEPARTMENT_NAME = "IT";

    public static EmployeeEntity getEmployeeEntity() {
        DepartmentEntity department = DepartmentEntity.builder()
                .withId(DEPARTMENT_ID)
                .withName(DEPARTMENT_NAME)
                .build();

        return EmployeeEntity.builder()
                .withId(EMPLOYEE_ID)
                .withFirstName(FIRST_NAME)
                .withLastName(LAST_NAME)
                .withPosition(POSITION)
                .withSalary(SALARY)
                .withDepartment(department)
                .build();
    }

    public static EmployeeRequestDto getEmployeeRequestDto() {
        return EmployeeRequestDto.builder()
                .withFirstName(FIRST_NAME)
                .withLastName(LAST_NAME)
                .withPosition(POSITION)
                .withSalary(SALARY)
                .withDepartmentName(DEPARTMENT_NAME)
                .build();
    }

    public static EmployeeResponseDto getEmployeeResponseDto() {
        return EmployeeResponseDto.builder()
                .withId(EMPLOYEE_ID)
                .withFirstName(FIRST_NAME)
                .withLastName(LAST_NAME)
                .withPosition(POSITION)
                .withSalary(SALARY)
                .withDepartmentName(DEPARTMENT_NAME)
                .build();
    }
}