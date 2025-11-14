package com.example.objectMapper.api.controllers;

import com.example.objectMapper.usercasses.OrderService;
import com.example.objectMapper.usercasses.dto.OrderRequestDto;
import com.example.objectMapper.usercasses.dto.OrderResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final ObjectMapper objectMapper;

    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'MODERATOR', 'SUPER_ADMIN')")
    public ResponseEntity<String> addOrder(@RequestBody String orderJson)
            throws JsonProcessingException {

        OrderRequestDto requestDto = objectMapper.readValue(
                orderJson, OrderRequestDto.class);
        OrderResponseDto responseDto = orderService.addOrder(requestDto);
        String responseJson = objectMapper.writeValueAsString(responseDto);

        return ResponseEntity.
                status(HttpStatus.CREATED).
                body(responseJson);
    }

    @GetMapping("/{orderId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> getOrder(@PathVariable UUID orderId)
            throws JsonProcessingException {

        OrderResponseDto order = orderService.getOrder(orderId);
        String orderJson = objectMapper.writeValueAsString(order);

        return ResponseEntity.
                ok(orderJson);
    }

    @PutMapping("/{orderId}")
    @PreAuthorize("hasAnyRole('MODERATOR', 'SUPER_ADMIN')")
    public ResponseEntity<String> updateOrder(
            @PathVariable UUID orderId,
            @RequestBody String orderJson) throws JsonProcessingException {

        OrderRequestDto requestDto = objectMapper.readValue(orderJson, OrderRequestDto.class);
        OrderResponseDto responseDto = orderService.updateOrder(orderId, requestDto);
        String responseJson = objectMapper.writeValueAsString(responseDto);

        return ResponseEntity.
                ok(responseJson);
    }

    @DeleteMapping("/{orderId}")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<Void> deleteOrder(@PathVariable UUID orderId) {
        orderService.deleteOrder(orderId);

        return ResponseEntity.
                noContent().
                build();
    }
}