package com.example.objectMapper.usercasses.impl;

import com.example.objectMapper.api.exeption.NotFoundException;
import com.example.objectMapper.persistence.model.ProductEntity;
import com.example.objectMapper.persistence.repository.ProductRepository;
import com.example.objectMapper.usercasses.ProductService;
import com.example.objectMapper.usercasses.dto.ProductRequestDto;
import com.example.objectMapper.usercasses.dto.ProductResponseDto;
import com.example.objectMapper.usercasses.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepo;


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
        ProductEntity productEntity = getProductRepoByID(productId);
        ProductResponseDto productResponseDto = productMapper.fromEntityToDto(productEntity);

        log.info("The product with the id {} FOUND. Time: {}"
                , productId, LocalDateTime.now());

        return productResponseDto;
    }

    @Override
    public ProductResponseDto updateProduct(UUID productId, ProductRequestDto productRequestDto) {
        return null;
    }

    @Override
    public void deleteProduct(UUID productId) {
        ProductEntity productEntity = getProductRepoByID(productId);
        productRepo.deleteById(productId);

        log.info("The product with the id {} has been DELETED, Date {}"
                , productId, LocalDateTime.now());
    }

    private ProductEntity getProductRepoByID(UUID productId) {

        return productRepo.findById(productId)
                .orElseThrow(() ->
                        new NotFoundException("Product not found. FAIL! ID: " + productId));
    }
}
