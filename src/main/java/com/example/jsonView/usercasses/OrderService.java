package com.example.jsonView.usercasses;

import com.example.jsonView.usercasses.dto.OrderRequestDto;
import com.example.jsonView.usercasses.dto.OrderResponseDto;
import com.example.jsonView.usercasses.dto.OrderStatus;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    OrderResponseDto addOrder(OrderRequestDto orderRequestDto);

    OrderResponseDto getOrderById(UUID orderId);

    List<OrderResponseDto> getOrdersByUserId(UUID userId);

    OrderResponseDto updateOrderStatusById(UUID orderId, OrderStatus orderStatus);

    void deleteOrderById(UUID orderId);
}
