package com.example.springDataProjections.usercasses;

import com.example.springDataProjections.usercasses.dto.DepartmentRequestDto;
import com.example.springDataProjections.usercasses.dto.DepartmentResponseDto;
import com.example.springDataProjections.usercasses.dto.EmployeeRequestDto;
import com.example.springDataProjections.usercasses.dto.EmployeeResponseDto;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {

    EmployeeResponseDto addEmployee(EmployeeRequestDto employeeRequestDto);

    EmployeeResponseDto getEmployee(UUID id);

    List<EmployeeResponseDto> getAllEmployees();

    EmployeeResponseDto updateEmployee(UUID id, EmployeeRequestDto employeeRequestDto);

    void deleteEmployee(UUID id);
}
