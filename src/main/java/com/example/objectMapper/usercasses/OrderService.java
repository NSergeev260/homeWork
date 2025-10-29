package com.example.objectMapper.usercasses;

import com.example.objectMapper.usercasses.dto.OrderRequestDto;
import com.example.objectMapper.usercasses.dto.OrderResponseDto;

import java.util.UUID;

public interface OrderService {

    OrderResponseDto addOrder(OrderRequestDto orderRequestDto);

    OrderResponseDto getOrder(UUID orderId);

    OrderResponseDto updateOrder(UUID orderId, OrderRequestDto orderRequestDto);

    void deleteOrder(UUID orderId);
}
