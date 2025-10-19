package com.example.jsonView.api.controllers;

import com.example.jsonView.api.view.Views;
import com.example.jsonView.usercasses.OrderService;
import com.example.jsonView.usercasses.dto.OrderResponseDto;
import com.example.jsonView.usercasses.dto.OrderStatus;
import com.example.jsonView.usercasses.dto.Product;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("api/orders")
@RestController
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/{orderId}")
    public OrderResponseDto addOrder(UUID orderId, List<Product> productList) {
        return orderService.addOrder(orderId, productList);
    }

    @JsonView(Views.OrderSummary.class)
    @GetMapping
    public OrderResponseDto getOrder(UUID orderId) {
        return orderService.getOrderById(orderId);
    }

    @JsonView(Views.OrderSummary.class)
    @GetMapping
    public List<OrderResponseDto> getOrdersByUser(UUID userId) {
      return orderService.getOrdersByUserId(userId);
    }

    @PutMapping("/{orderId}")
    public OrderResponseDto updateOrderStatus(UUID orderId, OrderStatus orderStatus) {
        return orderService.updateOrderStatusById(orderId, orderStatus);
    }

    @DeleteMapping("/{orderId}")
    public void deleteOrder(UUID orderId) {
        orderService.deleteOrderById(orderId);
    }
}
