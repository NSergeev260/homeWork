package com.example.objectMapper.usercasses.mapper;

import com.example.objectMapper.persistence.model.CustomerEntity;
import com.example.objectMapper.usercasses.dto.CustomerRequestDto;
import com.example.objectMapper.usercasses.dto.CustomerResponseDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
public interface CustomerMapper {

    @Mapping(target = "customerId", ignore = true)
    CustomerEntity fromDtoToEntity(CustomerRequestDto customerRequestDto);

    CustomerResponseDto fromEntityToDto(CustomerEntity customerEntity);
}
