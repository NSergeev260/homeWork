package com.example.objectMapper.usercasses.impl;

import com.example.objectMapper.usercasses.OrderService;
import com.example.objectMapper.usercasses.dto.OrderRequestDto;
import com.example.objectMapper.usercasses.dto.OrderResponseDto;

import java.util.UUID;

public class OrderServiceImpl implements OrderService {
    @Override
    public OrderResponseDto addOrder(OrderRequestDto orderRequestDto) {
        return null;
    }

    @Override
    public OrderResponseDto getOrder(UUID orderId) {
        return null;
    }

    @Override
    public OrderResponseDto updateOrder(UUID orderId, OrderRequestDto orderRequestDto) {
        return null;
    }

    @Override
    public void deleteOrder(UUID orderId) {

    }
}
