package com.example.objectMapper.usercasses.impl;

import com.example.objectMapper.api.exeption.NotFoundException;
import com.example.objectMapper.persistence.model.OrderEntity;
import com.example.objectMapper.persistence.repository.OrderRepository;
import com.example.objectMapper.usercasses.OrderService;
import com.example.objectMapper.usercasses.dto.OrderRequestDto;
import com.example.objectMapper.usercasses.dto.OrderResponseDto;
import com.example.objectMapper.usercasses.mapper.OrderMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private  final OrderMapper orderMapper;
    private  final OrderRepository orderRepo;

    @Override
    public OrderResponseDto addOrder(OrderRequestDto orderRequestDto) {
        return null;
    }

    @Override
    public OrderResponseDto getOrder(UUID orderId) {
        OrderEntity orderEntity = getOrderRepoByID(orderId);
        OrderResponseDto orderResponseDto = orderMapper.fromEntityToDto(orderEntity);

        log.info("The order with the id {} FOUND. Time: {}"
                , orderId, LocalDateTime.now());

        return orderResponseDto;
    }

    @Override
    public OrderResponseDto updateOrder(UUID orderId, OrderRequestDto orderRequestDto) {
        return null;
    }

    @Override
    public void deleteOrder(UUID orderId) {
        OrderEntity orderEntity = getOrderRepoByID(orderId);
        orderRepo.deleteById(orderId);

        log.info("The order with the id {} has been DELETED, Date {}"
                , orderId, LocalDateTime.now());
    }

    private OrderEntity getOrderRepoByID(UUID orderId) {

        return orderRepo.findById(orderId)
                .orElseThrow(() ->
                        new NotFoundException("Order not found. FAIL! ID: " + orderId));
    }
}
