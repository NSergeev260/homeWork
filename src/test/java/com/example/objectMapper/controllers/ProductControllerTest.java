package com.example.objectMapper.controllers;

import com.example.objectMapper.api.controllers.ProductController;
import com.example.objectMapper.usercasses.ProductService;
import com.example.objectMapper.usercasses.dto.ProductRequestDto;
import com.example.objectMapper.usercasses.dto.ProductResponseDto;
import com.example.objectMapper.util.ProductTestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Test
    void methodShouldAddProductTest() {
        ProductRequestDto request = ProductTestData.getProductRequestDto();
        ProductResponseDto response = ProductTestData.getProductResponseDto();

        Mockito.when(productService.addProduct(request))
                .thenReturn(response);

        ResponseEntity<ProductResponseDto> result = productController.addProduct(request);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.CREATED, result.getStatusCode());
        Assertions.assertNotNull(result.getBody());
        Assertions.assertEquals(response.productId(), result.getBody().productId());
        Assertions.assertEquals(response.name(), result.getBody().name());
        Assertions.assertEquals(response.price(), result.getBody().price());
        Mockito.verify(productService).addProduct(request);
    }

    @Test
    void methodShouldGetProductByIdTest() {
        ProductResponseDto response = ProductTestData.getProductResponseDto();
        UUID productId = ProductTestData.PRODUCT_ID;

        Mockito.when(productService.getProduct(productId))
                .thenReturn(response);

        ResponseEntity<ProductResponseDto> result = productController.getProduct(productId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertNotNull(result.getBody());
        Assertions.assertEquals(productId, result.getBody().productId());
        Assertions.assertEquals(ProductTestData.PRODUCT_NAME, result.getBody().name());
        Assertions.assertEquals(ProductTestData.PRODUCT_PRICE, result.getBody().price());
        Mockito.verify(productService).getProduct(productId);
    }

    @Test
    void methodShouldGetAllProductsTest() {
        ProductResponseDto productResponse = ProductTestData.getProductResponseDto();
        List<ProductResponseDto> products = Arrays.asList(productResponse);

        Mockito.when(productService.getAllProducts())
                .thenReturn(products);

        ResponseEntity<List<ProductResponseDto>> result = productController.getAllProducts();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertNotNull(result.getBody());
        Assertions.assertEquals(1, result.getBody().size());
        Assertions.assertEquals(ProductTestData.PRODUCT_NAME, result.getBody().get(0).name());
        Assertions.assertEquals(ProductTestData.PRODUCT_PRICE, result.getBody().get(0).price());
        Mockito.verify(productService).getAllProducts();
    }

    @Test
    void methodShouldUpdateProductTest() {
        ProductRequestDto request = ProductTestData.getProductRequestDto();
        ProductResponseDto response = ProductTestData.getProductResponseDto();
        UUID productId = ProductTestData.PRODUCT_ID;

        Mockito.when(productService.updateProduct(productId, request)).thenReturn(response);

        ResponseEntity<ProductResponseDto> result = productController.updateProduct(productId, request);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertNotNull(result.getBody());
        Assertions.assertEquals(response.productId(), result.getBody().productId());
        Assertions.assertEquals(response.name(), result.getBody().name());
        Mockito.verify(productService).updateProduct(productId, request);
    }

    @Test
    void methodShouldDeleteProductTest() {
        UUID productId = ProductTestData.PRODUCT_ID;

        ResponseEntity<Void> result = productController.deleteProduct(productId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        Mockito.verify(productService).deleteProduct(productId);
    }
}