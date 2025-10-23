package com.example.jsonView.api.util;

import com.example.jsonView.persistence.model.OrderEntity;
import com.example.jsonView.persistence.model.Product;
import com.example.jsonView.persistence.model.UserEntity;
import com.example.jsonView.usercasses.dto.OrderRequestDto;
import com.example.jsonView.usercasses.dto.OrderResponseDto;
import com.example.jsonView.usercasses.dto.OrderStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderTestData {

    public static final UUID ORDER_ID = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
    public static final List<Product> PRODUCTS_LIST = createProductsList();
    public static final BigDecimal ORDER_AMOUNT = new BigDecimal("100.00");
    public static OrderStatus ORDER_STATUS = OrderStatus.PENDING;
    public static final UUID USER_ID = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

    private static List<Product> createProductsList() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(UUID.randomUUID(),"Laptop", new BigDecimal("50.00")));
        products.add(new Product(UUID.randomUUID(),"Mouse", new BigDecimal("20.00")));
        products.add(new Product(UUID.randomUUID(),"Keyboard", new BigDecimal("30.00")));
        return products;
    }

    public static OrderRequestDto getOrderRequestDto() {

        return OrderRequestDto.builder()
                .withProductsList(PRODUCTS_LIST)
                .withOrderAmount(ORDER_AMOUNT)
                .withOrderStatus(ORDER_STATUS)
                .withUserId(USER_ID)
                .build();
    }

    public static OrderResponseDto getOrderResponseDto() {

        return OrderResponseDto.builder()
                .withOrderId(ORDER_ID)
                .withProductsList(PRODUCTS_LIST)
                .withOrderAmount(ORDER_AMOUNT)
                .withOrderStatus(ORDER_STATUS)
                .build();
    }

    public static OrderEntity getOrderEntity(UserEntity userEntity) {

        return OrderEntity.builder()
                .withOrderId(ORDER_ID)
                .withOrderAmount(ORDER_AMOUNT)
                .withProductsList(PRODUCTS_LIST)
                .withOrderStatus(ORDER_STATUS)
                .withUserEntity(userEntity)
                .build();
    }
}
