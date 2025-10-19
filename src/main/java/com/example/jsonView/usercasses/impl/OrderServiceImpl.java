package com.example.jsonView.usercasses.impl;

import com.example.jsonView.api.exeption.BadRequestException;
import com.example.jsonView.persistence.model.OrderEntity;
import com.example.jsonView.persistence.repository.OrderRepository;
import com.example.jsonView.usercasses.OrderService;
import com.example.jsonView.usercasses.dto.OrderStatus;
import com.example.jsonView.usercasses.dto.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepo;

        @Override
    public OrderService insertOrder(UUID orderId, List<Product> productInfo, OrderStatus statusOrder) {
        return null;
    }

    @Override
    public OrderService getOrder(UUID orderId) {
        return null;
    }

    @Override
    public OrderService updateOrder(UUID orderId) {
        return null;
    }

    @Override
    public void deleteOrder(UUID orderId) {
        OrderEntity orderEntity = getOrderRepoByID(orderId);
        orderRepo.delete(orderEntity);

        log.info("User with id {} was DELETE, Date: {}", orderId, LocalDateTime.now());
    }

    private OrderEntity getOrderRepoByID(UUID orderId) {
        OrderEntity orderEntity = orderRepo.findById(orderId)
                .orElseThrow(() ->
                        new BadRequestException("Order not exists. FAIL! ID: " + orderId));
        return orderEntity;
    }
}
