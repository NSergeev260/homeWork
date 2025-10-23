package com.example.jsonView.usercasses.impl;

import com.example.jsonView.api.exeption.BadRequestException;
import com.example.jsonView.api.exeption.NotFoundException;
import com.example.jsonView.persistence.model.OrderEntity;
import com.example.jsonView.persistence.model.Product;
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

    @Transactional
    @Override
    public OrderResponseDto addOrder(OrderRequestDto orderRequestDto) {
        UserEntity user = userRepo.findById(orderRequestDto.userId())
                .orElseThrow(() ->
                        new NotFoundException("User NOT found with id: " + orderRequestDto.userId()));
        BigDecimal orderAmount = orderRequestDto.productsList().stream()
                .map(Product::getProductCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        OrderEntity orderEntity = orderMapper.fromDtoToEntity(orderRequestDto);
        orderEntity.setUserEntity(user);
        orderEntity.setOrderAmount(orderAmount);
        orderEntity.setOrderStatus(OrderStatus.PENDING);
        OrderEntity savedOrder = orderRepo.save(orderEntity);

        log.info("New order with id {} was INSERT, User: {} ,Time: {}",
                orderEntity.getOrderId(), user.getUserId(), LocalDateTime.now());

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
        userRepo.findById(userId)
                .orElseThrow(() ->
                        new BadRequestException("User not exists. FAIL! ID: " + userId));
        List<OrderEntity> orders = orderRepo.findByUserEntityUserId(userId);
        return orderMapper.fromEntityListToDtoList(orders);
    }

    @Transactional
    @Override
    public OrderResponseDto updateOrderStatusById(UUID orderId, OrderStatus orderStatus) {
        OrderEntity orderEntity = getOrderRepoByID(orderId);
        orderEntity.setOrderStatus(orderStatus);
        OrderEntity updatedOrder = orderRepo.save(orderEntity);

        log.info("Order with id {} was UPDATED to status {}, Time: {}",
                orderId, orderStatus, LocalDateTime.now());

        return orderMapper.fromEntityToDto(updatedOrder);
    }

    @Transactional
    @Override
    public void deleteOrderById(UUID orderId) {
        OrderEntity orderEntity = getOrderRepoByID(orderId);
        orderRepo.delete(orderEntity);

        log.info("Order with id {} was DELETE, Date: {}", orderId, LocalDateTime.now());
    }

    private OrderEntity getOrderRepoByID(UUID orderId) {
        OrderEntity orderEntity = orderRepo.findById(orderId)
                .orElseThrow(() ->
                        new NotFoundException("Order not exists. FAIL! ID: " + orderId));
        return orderEntity;
    }
}
