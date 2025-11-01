package com.example.objectMapper.util;

import com.example.objectMapper.persistence.model.OrderEntity;
import com.example.objectMapper.persistence.model.ProductEntity;
import com.example.objectMapper.usercasses.dto.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class OrderTestData {

    public static final UUID ORDER_ID = UUID.fromString("e3f4a5b6-c7d8-4012-8f34-56789abcdef1");
    public static final LocalDateTime ORDER_DATE = LocalDateTime.of(2026, 1, 15, 14, 30);
    public static final String SHIPPING_ADDRESS = "г. Москва, ул. Ленина, д. 25, кв. 12";
    public static final BigDecimal TOTAL_PRICE = new BigDecimal("179999.98");
    public static final OrderStatus ORDER_STATUS = OrderStatus.PENDING;
    public static final UUID SECOND_PRODUCT_ID = UUID.fromString("f4a5b6c7-d8e9-4123-8f56-789abcdef123");

    public static OrderEntity getOrderEntity() {
        return OrderEntity.builder()
                .withOrderId(ORDER_ID)
                .withCustomer(CustomerTestData.getCustomerEntity())
                .withProducts(getSampleProducts())
                .withOrderDate(ORDER_DATE)
                .withShippingAddress(SHIPPING_ADDRESS)
                .withTotalPrice(TOTAL_PRICE)
                .withOrderStatus(ORDER_STATUS)
                .build();
    }

    public static OrderRequestDto getOrderRequestDto() {
        List<String> productIds = Arrays.asList(
                ProductTestData.PRODUCT_ID.toString(),
                SECOND_PRODUCT_ID.toString()
        );
        
        return OrderRequestDto.builder()
                .withCustomerId(CustomerTestData.CUSTOMER_ID)
                .withProducts(productIds)
                .withOrderDate(ORDER_DATE)
                .withShippingAddress(SHIPPING_ADDRESS)
                .withOrderStatus(ORDER_STATUS)
                .build();
    }

    public static OrderResponseDto getOrderResponseDto() {
        List<ProductResponseDto> products = Arrays.asList(
                ProductTestData.getProductResponseDto(),
                ProductResponseDto.builder()
                        .withProductId(SECOND_PRODUCT_ID)
                        .withName("Чехол")
                        .withDescription("Защитный чехол из силикона")
                        .withPrice(new BigDecimal("1999.99"))
                        .withQuantityInStock(100L)
                        .build()
        );

        return OrderResponseDto.builder()
                .withOrderId(ORDER_ID)
                .withCustomer(CustomerTestData.getCustomerResponseDto())
                .withProducts(products)
                .withOrderDate(ORDER_DATE)
                .withShippingAddress(SHIPPING_ADDRESS)
                .withTotalPrice(TOTAL_PRICE)
                .withOrderStatus(ORDER_STATUS)
                .build();
    }

    public static List<ProductEntity> getSampleProducts() {
        return Arrays.asList(
                ProductTestData.getProductEntity(),
                ProductEntity.builder()
                        .withProductId(SECOND_PRODUCT_ID)
                        .withName("Чехол")
                        .withDescription("Защитный чехол из силикона")
                        .withPrice(new BigDecimal("1999.99"))
                        .withQuantityInStock(100L)
                        .build()
        );
    }
}