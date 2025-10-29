package com.example.objectMapper.usercasses;

import com.example.objectMapper.usercasses.dto.ProductRequestDto;
import com.example.objectMapper.usercasses.dto.ProductResponseDto;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    ProductResponseDto addProduct(ProductRequestDto productRequestDto);

    List<ProductResponseDto> getAllProducts();

    ProductResponseDto getProduct(UUID productId);

    ProductResponseDto updateProduct(UUID productId, ProductRequestDto productRequestDto);

    void deleteProduct(UUID productId);
}
