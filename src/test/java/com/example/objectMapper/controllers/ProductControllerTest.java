package com.example.objectMapper.controllers;

import com.example.objectMapper.api.controllers.ProductController;
import com.example.objectMapper.usercasses.ProductService;
import com.example.objectMapper.usercasses.dto.ProductRequestDto;
import com.example.objectMapper.usercasses.dto.ProductResponseDto;
import com.example.objectMapper.util.ProductTestData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private ProductController productController;

    @Test
    void methodShouldAddProductTest() throws JsonProcessingException {
        ProductRequestDto request = ProductTestData.getProductRequestDto();
        ProductResponseDto response = ProductTestData.getProductResponseDto();
        String requestJson = "{\"name\":\"Test Product\",\"price\":100}";
        String responseJson = "{\"productId\":\"123\",\"name\":\"Test Product\"}";

        Mockito.when(objectMapper.readValue(requestJson, ProductRequestDto.class))
                .thenReturn(request);
        Mockito.when(productService.addProduct(request))
                .thenReturn(response);
        Mockito.when(objectMapper.writeValueAsString(response))
                .thenReturn(responseJson);

        ResponseEntity<String> result = productController.addProduct(requestJson);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.CREATED, result.getStatusCode());
        Assertions.assertEquals(responseJson, result.getBody());
        Mockito.verify(productService).addProduct(request);
    }

    @Test
    void methodShouldGetProductByIdTest() throws JsonProcessingException {
        ProductResponseDto response = ProductTestData.getProductResponseDto();
        UUID productId = ProductTestData.PRODUCT_ID;
        String responseJson = "{\"productId\":\"123\",\"name\":\"Test Product\"}";

        Mockito.when(productService.getProduct(productId))
                .thenReturn(response);
        Mockito.when(objectMapper.writeValueAsString(response))
                .thenReturn(responseJson);

        ResponseEntity<String> result = productController.getProduct(productId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(responseJson, result.getBody());
        Mockito.verify(productService).getProduct(productId);
    }

    @Test
    void methodShouldGetAllProductsTest() throws JsonProcessingException {
        ProductResponseDto productResponse = ProductTestData.getProductResponseDto();
        List<ProductResponseDto> products = Arrays.asList(productResponse);
        String responseJson = "[{\"productId\":\"123\",\"name\":\"Test Product\"}]";

        Mockito.when(productService.getAllProducts())
                .thenReturn(products);
        Mockito.when(objectMapper.writeValueAsString(products))
                .thenReturn(responseJson);

        ResponseEntity<String> result = productController.getAllProducts();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(responseJson, result.getBody());
        Mockito.verify(productService).getAllProducts();
    }

    @Test
    void methodShouldUpdateProductTest() throws JsonProcessingException {
        ProductRequestDto request = ProductTestData.getProductRequestDto();
        ProductResponseDto response = ProductTestData.getProductResponseDto();
        UUID productId = ProductTestData.PRODUCT_ID;
        String requestJson = "{\"name\":\"Updated Product\",\"price\":150}";
        String responseJson = "{\"productId\":\"123\",\"name\":\"Updated Product\"}";

        Mockito.when(objectMapper.readValue(requestJson, ProductRequestDto.class))
                .thenReturn(request);
        Mockito.when(productService.updateProduct(productId, request))
                .thenReturn(response);
        Mockito.when(objectMapper.writeValueAsString(response))
                .thenReturn(responseJson);

        ResponseEntity<String> result = productController.updateProduct(productId, requestJson);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(responseJson, result.getBody());
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