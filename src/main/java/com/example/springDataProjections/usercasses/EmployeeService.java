package com.example.springDataProjections.usercasses;

import com.example.springDataProjections.persistence.projection.EmployeeProjection;
import com.example.springDataProjections.persistence.projection.EmployeeSalaryProjection;
import com.example.springDataProjections.usercasses.dto.EmployeeRequestDto;
import com.example.springDataProjections.usercasses.dto.EmployeeResponseDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeService {

    EmployeeResponseDto addEmployee(EmployeeRequestDto employeeRequestDto);

    EmployeeResponseDto getEmployee(UUID id);

    List<EmployeeResponseDto> getAllEmployees();

    Optional<EmployeeProjection> getEmployeeProjectionById(UUID id);

    List<EmployeeSalaryProjection> getEmployeeSalaryProjections();

    EmployeeResponseDto updateEmployee(UUID id, EmployeeRequestDto employeeRequestDto);

    void deleteEmployee(UUID id);

}
