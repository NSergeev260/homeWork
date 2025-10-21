package com.example.jsonView.usercasses.impl;

import com.example.jsonView.api.exeption.NotFoundException;
import com.example.jsonView.persistence.model.OrderEntity;
import com.example.jsonView.persistence.model.UserEntity;
import com.example.jsonView.persistence.repository.OrderRepository;
import com.example.jsonView.persistence.repository.UserRepository;
import com.example.jsonView.usercasses.OrderService;
import com.example.jsonView.usercasses.dto.*;
import com.example.jsonView.usercasses.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepo;
    private final OrderMapper orderMapper;
    private final UserRepository userRepo;

    @Override
    @Transactional
    public OrderResponseDto addOrder(OrderRequestDto orderRequestDto) {
        UserEntity user = userRepo.findById(orderRequestDto.userId())
                .orElseThrow(() ->
                        new NotFoundException("User not found with id: " + orderRequestDto.userId()));

        BigDecimal orderAmount = orderRequestDto.productsList().stream()
                .map(ProductResponseDto::productCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        OrderEntity orderEntity = orderMapper.fromDtoToEntity(orderRequestDto);
        orderEntity.setUser(user);
        orderEntity.setOrderAmount(orderAmount);
        orderEntity.setOrderStatus(OrderStatus.PENDING);

        OrderEntity savedOrder = orderRepo.save(orderEntity);

        log.info("New order with id {} was INSERT for user {}, Time: {}", 
                savedOrder.getOrderId(), user.getUserId(), LocalDateTime.now());

        return orderMapper.fromEntityToDto(savedOrder);
    }

    @Override
    public OrderResponseDto getOrderById(UUID orderId) {
        OrderEntity orderEntity = getOrderRepoByID(orderId);
        log.info("Order with id {} was found. Time: {}", orderId, LocalDateTime.now());
        return orderMapper.fromEntityToDto(orderEntity);
    }

    @Override
    public List<OrderResponseDto> getOrdersByUserId(UUID userId) {
        if (!userRepo.existsById(userId)) {
            throw new NotFoundException("User not found with id: " + userId);
        }
        
        List<OrderEntity> orders = orderRepo.findByUserId(userId);
        return orderMapper.fromEntityListToDtoList(orders);
    }

    @Override
    @Transactional
    public OrderResponseDto updateOrderStatusById(UUID orderId, OrderStatus orderStatus) {
        OrderEntity orderEntity = getOrderRepoByID(orderId);
        orderEntity.setOrderStatus(orderStatus);
        OrderEntity updatedOrder = orderRepo.save(orderEntity);

        log.info("Order with id {} was UPDATED to status {}, Time: {}",
                orderId, orderStatus, LocalDateTime.now());

        return orderMapper.fromEntityToDto(updatedOrder);
    }

    @Override
    @Transactional
    public void deleteOrderById(UUID orderId) {
        OrderEntity orderEntity = getOrderRepoByID(orderId);
        orderRepo.delete(orderEntity);
        log.info("Order with id {} was DELETE, Date: {}", orderId, LocalDateTime.now());
    }

    private OrderEntity getOrderRepoByID(UUID orderId) {
        return orderRepo.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order not found with id: " + orderId));
    }
}