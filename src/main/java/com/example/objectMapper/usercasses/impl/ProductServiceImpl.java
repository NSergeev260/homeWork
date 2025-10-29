package com.example.objectMapper.usercasses.impl;

import com.example.objectMapper.usercasses.ProductService;
import com.example.objectMapper.usercasses.dto.ProductRequestDto;
import com.example.objectMapper.usercasses.dto.ProductResponseDto;

import java.util.List;
import java.util.UUID;

public class ProductServiceImpl implements ProductService {
    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) {
        return null;
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        return List.of();
    }

    @Override
    public ProductResponseDto getProduct(UUID productId) {
        return null;
    }

    @Override
    public ProductResponseDto updateProduct(UUID productId, ProductRequestDto productRequestDto) {
        return null;
    }

    @Override
    public void deleteProduct(UUID productId) {

    }
}
