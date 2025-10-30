package com.example.objectMapper.util;

import com.example.objectMapper.persistence.model.ProductEntity;
import com.example.objectMapper.usercasses.dto.ProductRequestDto;
import com.example.objectMapper.usercasses.dto.ProductResponseDto;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductTestData {

    public static final UUID PRODUCT_ID = UUID.fromString("d2e3f4a5-b6c7-8901-def2-3456789abcde");
    public static final String PRODUCT_NAME = "Samsung Galaxy S23";
    public static final String PRODUCT_DESCRIPTION = "Флагманский смартфон с камерой 200 МП";
    public static final BigDecimal PRODUCT_PRICE = new BigDecimal("89999.99");
    public static final Long QUANTITY_IN_STOCK = 50L;

    public static ProductEntity getProductEntity() {
        return ProductEntity.builder()
                .withProductId(PRODUCT_ID)
                .withName(PRODUCT_NAME)
                .withDescription(PRODUCT_DESCRIPTION)
                .withPrice(PRODUCT_PRICE)
                .withQuantityInStock(QUANTITY_IN_STOCK)
                .build();
    }

    public static ProductRequestDto getProductRequestDto() {
        return ProductRequestDto.builder()
                .withName(PRODUCT_NAME)
                .withDescription(PRODUCT_DESCRIPTION)
                .withPrice(PRODUCT_PRICE)
                .withQuantityInStock(QUANTITY_IN_STOCK)
                .build();
    }

    public static ProductResponseDto getProductResponseDto() {
        return ProductResponseDto.builder()
                .withProductId(PRODUCT_ID)
                .withName(PRODUCT_NAME)
                .withDescription(PRODUCT_DESCRIPTION)
                .withPrice(PRODUCT_PRICE)
                .withQuantityInStock(QUANTITY_IN_STOCK)
                .build();
    }
}