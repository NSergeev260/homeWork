package com.example.springDataProjections.usercasses.mapper;

import com.example.springDataProjections.persistence.model.DepartmentEntity;
import com.example.springDataProjections.usercasses.dto.DepartmentRequestDto;
import com.example.springDataProjections.usercasses.dto.DepartmentResponseDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
public interface DepartmentMapper {

    DepartmentEntity fromDtoToEntity(DepartmentRequestDto departmentRequestDto);

    DepartmentResponseDto fromEntityToDto(DepartmentEntity departmentEntity);
}

