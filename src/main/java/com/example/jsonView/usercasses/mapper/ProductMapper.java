package com.example.jsonView.usercasses.mapper;

import com.example.jsonView.persistence.model.Product;
import com.example.jsonView.usercasses.dto.ProductResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

    ProductResponseDto toDto(Product product);

    Product toEntity(ProductResponseDto dto);
}