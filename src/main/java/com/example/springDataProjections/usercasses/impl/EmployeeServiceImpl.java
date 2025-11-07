package com.example.springDataProjections.usercasses.impl;

import com.example.springDataProjections.usercasses.EmployeeService;
import com.example.springDataProjections.usercasses.dto.EmployeeRequestDto;
import com.example.springDataProjections.usercasses.dto.EmployeeResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public EmployeeResponseDto addEmployee(EmployeeRequestDto employeeRequestDto) {
        return null;
    }

    @Override
    public EmployeeResponseDto getEmployee(UUID id) {
        return null;
    }

    @Override
    public List<EmployeeResponseDto> getAllEmployees() {
        return List.of();
    }

    @Override
    public EmployeeResponseDto updateEmployee(UUID id, EmployeeRequestDto employeeRequestDto) {
        return null;
    }

    @Override
    public void deleteEmployee(UUID id) {

    }
}
