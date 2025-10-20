package com.example.jsonView.api.controllers;

import com.example.jsonView.api.view.Views;
import com.example.jsonView.usercasses.OrderService;
import com.example.jsonView.usercasses.dto.OrderRequestDto;
import com.example.jsonView.usercasses.dto.OrderResponseDto;
import com.example.jsonView.usercasses.dto.OrderStatus;
import com.example.jsonView.usercasses.dto.Product;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("api/orders")
@RestController
public class OrderController {

    private final OrderService orderService;

    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping("/{orderId}")
    public OrderResponseDto addOrder(@RequestBody OrderRequestDto orderRequestDto) {
        return orderService.addOrder(orderRequestDto);
    }

    @JsonView(Views.OrderDetails.class)
    @GetMapping("/{orderId}")
    public OrderResponseDto getOrder(@PathVariable UUID orderId) {
        return orderService.getOrderById(orderId);
    }

    @JsonView(Views.OrderSummary.class)
    @GetMapping("/user/{userId}")
    public List<OrderResponseDto> getOrdersByUser(@PathVariable UUID userId) {
        return orderService.getOrdersByUserId(userId);
    }

    @PutMapping("/{orderId}/status")
    public OrderResponseDto updateOrderStatus(@PathVariable UUID orderId,
                                              @RequestParam OrderStatus orderStatus) {
        return orderService.updateOrderStatusById(orderId, orderStatus);
    }

    @DeleteMapping("/{orderId}")
    public void deleteOrder(@PathVariable UUID orderId) {
        orderService.deleteOrderById(orderId);
    }
}
