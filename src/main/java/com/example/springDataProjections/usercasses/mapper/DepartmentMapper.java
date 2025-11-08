package com.example.springDataProjections.usercasses.mapper;

import com.example.springDataProjections.persistence.model.DepartmentEntity;
import com.example.springDataProjections.usercasses.dto.DepartmentRequestDto;
import com.example.springDataProjections.usercasses.dto.DepartmentResponseDto;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {
    
    public DepartmentEntity dtoToEntity(DepartmentRequestDto dto) {
        return DepartmentEntity.builder()
            .withName(dto.name())
            .build();
    }
    
    public DepartmentResponseDto entityToDto(DepartmentEntity entity) {
        return DepartmentResponseDto.builder()
            .withId(entity.getId())
            .withName(entity.getName())
            .build();
    }
}