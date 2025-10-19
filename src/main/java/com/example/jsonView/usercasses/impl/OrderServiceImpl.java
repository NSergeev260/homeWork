package com.example.jsonView.usercasses.impl;

import com.example.jsonView.api.exeption.BadRequestException;
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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    public OrderResponseDto addOrder(UUID orderId, List<Product> productList) {
        UUID newOrderId = UUID.randomUUID();

        if (orderRepo.findById(newOrderId).isPresent()) {
            log.info("Order with id {} already exists. FAIL! Time: {}", newOrderId, LocalDateTime.now());
            throw new BadRequestException("Order already exists. FAIL!");
        }

        BigDecimal orderAmount = productList.stream()
                .map(Product::cost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        OrderRequestDto orderDto = OrderRequestDto.builder().
                withOrderId(newOrderId).
                withProductProduct(productList).
                withOrderAmount(orderAmount).
                withStatusOrder(OrderStatus.PENDING).
                build();

        OrderEntity orderEntity = orderMapper.fromDtoToEntity(orderDto);
        orderRepo.save(orderEntity);

        log.info("New order with id {} was INSERT, Time: {}", orderEntity.getOrderId(), LocalDateTime.now());

        return orderMapper.fromEntityToDto(orderEntity);
    }

    @Override
    public OrderResponseDto getOrderById(UUID orderId) {
        OrderEntity orderEntity = getOrderRepoByID(orderId);

        log.info("Order with id {} was found. Time: {}", orderId, LocalDateTime.now());

        return orderMapper.fromEntityToDto(orderEntity);
    }

    @Override
    public List<OrderResponseDto> getOrdersByUserId(UUID userId) {
        UserEntity userEntity = userRepo.findById(userId)
                .orElseThrow(() ->
                        new BadRequestException("User not exists. FAIL! ID: " + userId));
        List<OrderEntity> orders = orderRepo.findByUserId(userId);
        return orderMapper.fromEntityListToDtoList(orders);
    }

    @Override
    public OrderResponseDto updateOrderStatusById(UUID orderId, OrderStatus orderStatus) {
        OrderEntity orderEntity = getOrderRepoByID(orderId);
        orderEntity.setOrderStatus(orderStatus);
        orderRepo.save(orderEntity);

        log.info("Order with id {} was UPDATED to status {}, Time: {}",
                orderId, orderStatus, LocalDateTime.now());

        return orderMapper.fromEntityToDto(orderEntity);
    }

    @Override
    public void deleteOrderById(UUID orderId) {
        OrderEntity orderEntity = getOrderRepoByID(orderId);
        orderRepo.delete(orderEntity);

        log.info("Order with id {} was DELETE, Date: {}", orderId, LocalDateTime.now());
    }

    private OrderEntity getOrderRepoByID(UUID orderId) {
        OrderEntity orderEntity = orderRepo.findById(orderId)
                .orElseThrow(() ->
                        new BadRequestException("Order not exists. FAIL! ID: " + orderId));
        return orderEntity;
    }
}
