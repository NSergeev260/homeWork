package com.example.objectMapper.usercasses.mapper;

import com.example.objectMapper.persistence.model.ProductEntity;
import com.example.objectMapper.usercasses.dto.ProductRequestDto;
import com.example.objectMapper.usercasses.dto.ProductResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductResponseDto fromEntityToDto(ProductEntity productEntity) {

        if (productEntity == null) {
            return null;
        }
        
        return ProductResponseDto.builder()
                .withProductId(productEntity.getProductId())
                .withName(productEntity.getName())
                .withDescription(productEntity.getDescription())
                .withPrice(productEntity.getPrice())
                .withQuantityInStock(productEntity.getQuantityInStock())
                .build();
    }

    @Override
    public ProductEntity fromDtoToEntity(ProductRequestDto productRequestDto) {

        if (productRequestDto == null) {
            return null;
        }
        
        return ProductEntity.builder()
                .withName(productRequestDto.name())
                .withDescription(productRequestDto.description())
                .withPrice(productRequestDto.price())
                .withQuantityInStock(productRequestDto.quantityInStock())
                .build();
    }
}