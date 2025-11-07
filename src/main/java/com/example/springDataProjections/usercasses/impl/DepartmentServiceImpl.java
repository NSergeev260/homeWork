package com.example.springDataProjections.usercasses.impl;

import com.example.springDataProjections.usercasses.DepartmentService;
import com.example.springDataProjections.usercasses.dto.DepartmentRequestDto;
import com.example.springDataProjections.usercasses.dto.DepartmentResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Override
    public DepartmentResponseDto addDepartment(DepartmentRequestDto departmentRequestDto) {
        return null;
    }

    @Override
    public DepartmentResponseDto getDepartment(UUID id) {
        return null;
    }

    @Override
    public List<DepartmentResponseDto> getAllDepartments() {
        return List.of();
    }

    @Override
    public DepartmentResponseDto updateDepartment(UUID id, DepartmentRequestDto departmentRequestDto) {
        return null;
    }

    @Override
    public void deleteDepartment(UUID id) {

    }
}
