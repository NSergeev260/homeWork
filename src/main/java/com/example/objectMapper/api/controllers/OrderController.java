package com.example.objectMapper.api.controllers;

import com.example.objectMapper.usercasses.OrderService;
import com.example.objectMapper.usercasses.dto.OrderRequestDto;
import com.example.objectMapper.usercasses.dto.OrderResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/api/orders")
@RestController
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> addOrder(
            @Valid @RequestBody OrderRequestDto orderRequestDto) {
        OrderResponseDto response = orderService.addOrder(orderRequestDto);
        return ResponseEntity.
                status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> getOrder(
            @PathVariable UUID orderId) {
        OrderResponseDto response = orderService.getOrder(orderId);
        return ResponseEntity.
                ok(response);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> updateOrder(
            @PathVariable UUID orderId,
            @Valid @RequestBody OrderRequestDto orderRequestDto) {
        OrderResponseDto response = orderService.updateOrder(orderId, orderRequestDto);
        return ResponseEntity.
                ok(response);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(
            @PathVariable UUID orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.
                noContent().
                build();
    }
}
