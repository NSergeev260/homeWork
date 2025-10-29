package com.example.objectMapper.usercasses.mapper;

import com.example.objectMapper.persistence.model.ProductEntity;
import com.example.objectMapper.usercasses.dto.ProductRequestDto;
import com.example.objectMapper.usercasses.dto.ProductResponseDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
public interface ProductMapper {

    ProductEntity fromDtoToEntity(ProductRequestDto productRequestDto);

    ProductResponseDto fromEntityToDto(ProductEntity productEntity);
}
