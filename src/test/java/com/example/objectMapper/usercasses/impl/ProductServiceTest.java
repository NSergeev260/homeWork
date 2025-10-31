package com.example.objectMapper.usercasses.impl;

import com.example.objectMapper.persistence.model.ProductEntity;
import com.example.objectMapper.persistence.repository.ProductRepository;
import com.example.objectMapper.usercasses.dto.ProductRequestDto;
import com.example.objectMapper.usercasses.dto.ProductResponseDto;
import com.example.objectMapper.usercasses.mapper.ProductMapper;
import com.example.objectMapper.util.ProductTestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductMapper productMapper;

    @Mock
    private ProductRepository productRepo;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void methodShouldAddProductTest() {
        ProductRequestDto requestDto = ProductTestData.getProductRequestDto();
        ProductEntity savedProduct = ProductTestData.getProductEntity();
        ProductResponseDto expectedResponse = ProductTestData.getProductResponseDto();

        Mockito.when(productRepo.findByName(ProductTestData.PRODUCT_NAME))
                .thenReturn(Optional.empty());
        Mockito.when(productRepo.save(any(ProductEntity.class)))
                .thenReturn(savedProduct);
        Mockito.when(productMapper.fromEntityToDto(savedProduct))
                .thenReturn(expectedResponse);

        ProductResponseDto result = productService.addProduct(requestDto);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(ProductTestData.PRODUCT_ID, result.productId());
        Assertions.assertEquals(ProductTestData.PRODUCT_NAME, result.name());
        Mockito.verify(productRepo).findByName(ProductTestData.PRODUCT_NAME);
        Mockito.verify(productRepo).save(any(ProductEntity.class));
    }

    @Test
    void methodShouldGetProductTest() {
        ProductEntity product = ProductTestData.getProductEntity();
        ProductResponseDto expectedResponse = ProductTestData.getProductResponseDto();

        Mockito.when(productRepo.findById(ProductTestData.PRODUCT_ID))
                .thenReturn(Optional.of(product));
        Mockito.when(productMapper.fromEntityToDto(product))
                .thenReturn(expectedResponse);

        ProductResponseDto result = productService.getProduct(ProductTestData.PRODUCT_ID);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(ProductTestData.PRODUCT_ID, result.productId());
        Assertions.assertEquals(ProductTestData.PRODUCT_NAME, result.name());
        Mockito.verify(productRepo).findById(ProductTestData.PRODUCT_ID);
        Mockito.verify(productMapper).fromEntityToDto(product);
    }

    @Test
    void methodShouldGetAllProductsTest() {
        ProductEntity product1 = ProductTestData.getProductEntity();
        ProductEntity product2 = ProductTestData.getProductEntity();
        
        List<ProductEntity> products = Arrays.asList(product1, product2);

        ProductResponseDto response = ProductTestData.getProductResponseDto();

        Mockito.when(productRepo.findAll()).thenReturn(products);
        Mockito.when(productMapper.fromEntityToDto(any(ProductEntity.class)))
                .thenReturn(response);

        List<ProductResponseDto> result = productService.getAllProducts();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        Mockito.verify(productRepo).findAll();
        Mockito.verify(productMapper, times(2))
                .fromEntityToDto(any(ProductEntity.class));
    }

    @Test
    void methodShouldUpdateProductTest() {
        ProductEntity existingProduct = ProductTestData.getProductEntity();
        ProductRequestDto updateRequest = new ProductRequestDto(
                "Samsung Galaxy S24",
                "Новый флагманский смартфон",
                new BigDecimal("99999.99"),
                25L
        );

        ProductEntity updatedProduct = ProductTestData.getProductEntity();
        updatedProduct.setName(updateRequest.name());
        updatedProduct.setDescription(updateRequest.description());
        updatedProduct.setPrice(updateRequest.price());
        updatedProduct.setQuantityInStock(updateRequest.quantityInStock());

        ProductResponseDto expectedResponse = ProductResponseDto.builder()
                .withProductId(ProductTestData.PRODUCT_ID)
                .withName("Samsung Galaxy S24")
                .withDescription("Новый флагманский смартфон")
                .withPrice(new BigDecimal("99999.99"))
                .withQuantityInStock(25L)
                .build();

        Mockito.when(productRepo.findById(ProductTestData.PRODUCT_ID))
                .thenReturn(Optional.of(existingProduct));
        Mockito.when(productRepo.save(any(ProductEntity.class)))
                .thenReturn(updatedProduct);
        Mockito.when(productMapper.fromEntityToDto(updatedProduct))
                .thenReturn(expectedResponse);

        ProductResponseDto result = productService
                .updateProduct(ProductTestData.PRODUCT_ID, updateRequest);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("Samsung Galaxy S24", result.name());
        Assertions.assertEquals("Новый флагманский смартфон", result.description());
        Assertions.assertEquals(new BigDecimal("99999.99"), result.price());
        Assertions.assertEquals(25L, result.quantityInStock());
        Mockito.verify(productRepo).findById(ProductTestData.PRODUCT_ID);
        Mockito.verify(productRepo).save(existingProduct);
    }

    @Test
    void methodShouldDeleteProductTest() {
        ProductEntity product = ProductTestData.getProductEntity();

        Mockito.when(productRepo.findById(ProductTestData.PRODUCT_ID))
                .thenReturn(Optional.of(product));
        Mockito.doNothing().when(productRepo).delete(product);

        productService.deleteProduct(ProductTestData.PRODUCT_ID);

        Mockito.verify(productRepo).findById(ProductTestData.PRODUCT_ID);
        Mockito.verify(productRepo).delete(product);
    }
}