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

        ProductResponseDto result = productController.addProduct(request);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(response.productId(), result.productId());
        Assertions.assertEquals(response.name(), result.name());
        Assertions.assertEquals(response.price(), result.price());
        Mockito.verify(productService).addProduct(request);
    }

    @Test
    void methodShouldGetProductByIdTest() {
        ProductResponseDto response = ProductTestData.getProductResponseDto();
        UUID productId = ProductTestData.PRODUCT_ID;

        Mockito.when(productService.getProduct(productId))
                .thenReturn(response);

        ProductResponseDto result = productController.getProduct(productId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(productId, result.productId());
        Assertions.assertEquals(ProductTestData.PRODUCT_NAME, result.name());
        Assertions.assertEquals(ProductTestData.PRODUCT_PRICE, result.price());
        Mockito.verify(productService).getProduct(productId);
    }

    @Test
    void methodShouldGetAllProductsTest() {
        ProductResponseDto productResponse = ProductTestData.getProductResponseDto();
        List<ProductResponseDto> products = Arrays.asList(productResponse);

        Mockito.when(productService.getAllProducts())
                .thenReturn(products);

        List<ProductResponseDto> result = productController.getAllProducts();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(ProductTestData.PRODUCT_NAME, result.get(0).name());
        Assertions.assertEquals(ProductTestData.PRODUCT_PRICE, result.get(0).price());
        Mockito.verify(productService).getAllProducts();
    }

    @Test
    void methodShouldUpdateProductTest() {
        ProductRequestDto request = ProductTestData.getProductRequestDto();
        ProductResponseDto response = ProductTestData.getProductResponseDto();
        UUID productId = ProductTestData.PRODUCT_ID;

        Mockito.when(productService.updateProduct(productId, request)).thenReturn(response);

        ProductResponseDto result = productController.updateProduct(productId, request);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(response.productId(), result.productId());
        Assertions.assertEquals(response.name(), result.name());
        Mockito.verify(productService).updateProduct(productId, request);
    }

    @Test
    void methodShouldDeleteProductTest() {
        UUID productId = ProductTestData.PRODUCT_ID;

        productController.deleteProduct(productId);

        Mockito.verify(productService).deleteProduct(productId);
    }
}