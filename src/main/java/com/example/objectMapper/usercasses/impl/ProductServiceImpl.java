package com.example.objectMapper.usercasses.impl;

import com.example.objectMapper.api.exeption.BadRequestException;
import com.example.objectMapper.api.exeption.NotFoundException;
import com.example.objectMapper.persistence.model.CustomerEntity;
import com.example.objectMapper.persistence.model.ProductEntity;
import com.example.objectMapper.persistence.repository.ProductRepository;
import com.example.objectMapper.usercasses.ProductService;
import com.example.objectMapper.usercasses.dto.ProductRequestDto;
import com.example.objectMapper.usercasses.dto.ProductResponseDto;
import com.example.objectMapper.usercasses.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepo;

    @Transactional
    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) {
        String nameOfProduct = productRequestDto.name();

        if (productRepo.findByName(nameOfProduct).isPresent()) {
            throw new BadRequestException("Product with name already EXIST. FAIL! name of product: " + nameOfProduct);
        }

        ProductEntity productEntity = productMapper.fromDtoToEntity(productRequestDto);
        ProductEntity addedProduct = productRepo.save(productEntity);

        log.info("The product with the id {} has been ADDED. Time: {}",
                addedProduct.getProductId(), LocalDateTime.now());

        return productMapper.fromEntityToDto(addedProduct);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductResponseDto> getAllProducts() {

        log.info("Method `getAllProducts` was run. Date {}", LocalDateTime.now());

        return productRepo.findAll().stream()
                .map(productMapper::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public ProductResponseDto getProduct(UUID productId) {
        ProductEntity productEntity = getProductRepoByID(productId);
        ProductResponseDto productResponseDto = productMapper.fromEntityToDto(productEntity);

        log.info("The product with the id {} FOUND. Time: {}"
                , productId, LocalDateTime.now());

        return productResponseDto;
    }

    @Transactional
    @Override
    public ProductResponseDto updateProduct(UUID productId, ProductRequestDto productRequestDto) {
        ProductEntity productEntity = getProductRepoByID(productId);
        productEntity.setName(productRequestDto.name());
        productEntity.setDescription(productRequestDto.description());
        productEntity.setPrice(productRequestDto.price());
        productEntity.setQuantityInStock(productRequestDto.quantityInStock());

        ProductEntity updatedProduct = productRepo.save(productEntity);

        log.info("The product with the id {} has been UPDATED, Date {}",
                updatedProduct.getProductId(), LocalDateTime.now());

        return productMapper.fromEntityToDto(updatedProduct);
    }

    @Transactional
    @Override
    public void deleteProduct(UUID productId) {
        ProductEntity productEntity = getProductRepoByID(productId);
        productRepo.delete(productEntity);

        log.info("The product with the id {} has been DELETED, Date {}"
                , productId, LocalDateTime.now());
    }

    private ProductEntity getProductRepoByID(UUID productId) {

        return productRepo.findById(productId)
                .orElseThrow(() ->
                        new NotFoundException("Product not found. FAIL! ID: " + productId));
    }
}
