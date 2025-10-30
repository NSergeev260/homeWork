package com.example.objectMapper.api.controllers;

import com.example.objectMapper.usercasses.OrderService;
import com.example.objectMapper.usercasses.dto.OrderRequestDto;
import com.example.objectMapper.usercasses.dto.OrderResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/api/orders")
@RestController
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public OrderResponseDto addOrder(@Valid @RequestBody OrderRequestDto orderRequestDto) {
        return orderService.addOrder(orderRequestDto);
    }

    @GetMapping("/{orderId}")
    public OrderResponseDto getOrder(@PathVariable UUID orderId) {
        return orderService.getOrder(orderId);
    }

    @PutMapping("/{orderId}")
    public OrderResponseDto updateOrder(@PathVariable UUID orderId,
                                        @Valid @RequestBody OrderRequestDto orderRequestDto) {
        return orderService.updateOrder(orderId, orderRequestDto);
    }

    @DeleteMapping("/{orderId}")
    public void deleteOrder(@PathVariable UUID orderId) {
        orderService.deleteOrder(orderId);
    }
}
