package com.example.objectMapper.controllers;

import com.example.objectMapper.api.controllers.OrderController;
import com.example.objectMapper.usercasses.OrderService;
import com.example.objectMapper.usercasses.dto.OrderRequestDto;
import com.example.objectMapper.usercasses.dto.OrderResponseDto;
import com.example.objectMapper.util.OrderTestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @Test
    void methodShouldAddOrderTest() {
        OrderRequestDto request = OrderTestData.getOrderRequestDto();
        OrderResponseDto response = OrderTestData.getOrderResponseDto();

        Mockito.when(orderService.addOrder(request))
                .thenReturn(response);

        ResponseEntity<OrderResponseDto> result = orderController.addOrder(request);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.CREATED, result.getStatusCode());
        Assertions.assertNotNull(result.getBody());
        Assertions.assertEquals(response.orderId(), result.getBody().orderId());
        Assertions.assertEquals(response.shippingAddress(), result.getBody().shippingAddress());
        Assertions.assertEquals(response.orderStatus(), result.getBody().orderStatus());
        Mockito.verify(orderService).addOrder(request);
    }

    @Test
    void methodShouldGetOrderByIdTest() {
        OrderResponseDto response = OrderTestData.getOrderResponseDto();
        UUID orderId = OrderTestData.ORDER_ID;

        Mockito.when(orderService.getOrder(orderId))
                .thenReturn(response);

        ResponseEntity<OrderResponseDto> result = orderController.getOrder(orderId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertNotNull(result.getBody());
        Assertions.assertEquals(orderId, result.getBody().orderId());
        Assertions.assertEquals(OrderTestData.SHIPPING_ADDRESS, result.getBody().shippingAddress());
        Assertions.assertEquals(OrderTestData.ORDER_STATUS, result.getBody().orderStatus());
        Mockito.verify(orderService).getOrder(orderId);
    }

    @Test
    void methodShouldUpdateOrderTest() {
        OrderRequestDto request = OrderTestData.getOrderRequestDto();
        OrderResponseDto response = OrderTestData.getOrderResponseDto();
        UUID orderId = OrderTestData.ORDER_ID;

        Mockito.when(orderService.updateOrder(orderId, request))
                .thenReturn(response);

        ResponseEntity<OrderResponseDto> result = orderController.updateOrder(orderId, request);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertNotNull(result.getBody());
        Assertions.assertEquals(response.orderId(), result.getBody().orderId());
        Assertions.assertEquals(response.shippingAddress(), result.getBody().shippingAddress());
        Mockito.verify(orderService).updateOrder(orderId, request);
    }

    @Test
    void methodShouldDeleteOrderTest() {
        UUID orderId = OrderTestData.ORDER_ID;

        ResponseEntity<Void> result = orderController.deleteOrder(orderId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        Mockito.verify(orderService).deleteOrder(orderId);
    }
}