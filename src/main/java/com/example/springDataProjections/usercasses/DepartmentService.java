package com.example.springDataProjections.usercasses;

import com.example.springDataProjections.usercasses.dto.DepartmentRequestDto;
import com.example.springDataProjections.usercasses.dto.DepartmentResponseDto;

import java.util.List;
import java.util.UUID;

public interface DepartmentService {

    DepartmentResponseDto addDepartment(DepartmentRequestDto departmentRequestDto);

    DepartmentResponseDto getDepartment(UUID id);

    List<DepartmentResponseDto> getAllDepartments();

    DepartmentResponseDto updateDepartment(UUID id, DepartmentRequestDto departmentRequestDto);

    void deleteDepartment(UUID id);


}
