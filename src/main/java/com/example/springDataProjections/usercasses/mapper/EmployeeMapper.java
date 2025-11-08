package com.example.springDataProjections.usercasses.mapper;

import com.example.springDataProjections.persistence.model.DepartmentEntity;
import com.example.springDataProjections.persistence.model.EmployeeEntity;
import com.example.springDataProjections.persistence.repository.DepartmentRepository;
import com.example.springDataProjections.usercasses.dto.EmployeeRequestDto;
import com.example.springDataProjections.usercasses.dto.EmployeeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeMapper {
    
    private final DepartmentRepository departmentRepository;
    
    public EmployeeEntity dtoToEntity(EmployeeRequestDto dto) {
        DepartmentEntity department = departmentRepository.findByName(dto.departmentName())
            .orElseThrow(() ->
                    new RuntimeException("Department not found, FAIL! name: " + dto.departmentName()));
        
        return EmployeeEntity.builder()
            .withFirstName(dto.firstName())
            .withLastName(dto.lastName())
            .withPosition(dto.position())
            .withSalary(dto.salary())
            .withDepartment(department)
            .build();
    }
    
    public EmployeeResponseDto entityToDto(EmployeeEntity entity) {
        return EmployeeResponseDto.builder()
            .withId(entity.getId())
            .withFirstName(entity.getFirstName())
            .withLastName(entity.getLastName())
            .withPosition(entity.getPosition())
            .withSalary(entity.getSalary())
            .withDepartmentName(entity.getDepartment().getName())
            .build();
    }
}