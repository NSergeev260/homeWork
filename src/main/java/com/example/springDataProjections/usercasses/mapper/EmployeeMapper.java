package com.example.springDataProjections.usercasses.mapper;

import com.example.springDataProjections.persistence.model.EmployeeEntity;
import com.example.springDataProjections.usercasses.dto.EmployeeRequestDto;
import com.example.springDataProjections.usercasses.dto.EmployeeResponseDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
public interface EmployeeMapper {

    EmployeeEntity fromDtoToEntity(EmployeeRequestDto employeeRequestDto);

    EmployeeResponseDto fromEntityToDto(EmployeeEntity employeeEntity);
}
