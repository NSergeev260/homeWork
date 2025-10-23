package com.example.jsonView.api.controllers;

import com.example.jsonView.api.util.OrderTestData;
import com.example.jsonView.usercasses.OrderService;
import com.example.jsonView.usercasses.dto.OrderRequestDto;
import com.example.jsonView.usercasses.dto.OrderResponseDto;
import com.example.jsonView.usercasses.dto.OrderStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
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

        Mockito.when(orderService.addOrder(request)).thenReturn(response);

        OrderResponseDto result = orderController.addOrder(request);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(response.orderId(), result.orderId());
        Mockito.verify(orderService).addOrder(request);
    }

    @Test
    void methodShouldGetOrderTest() {
        OrderResponseDto response = OrderTestData.getOrderResponseDto();
        UUID orderId = OrderTestData.ORDER_ID;

        Mockito.when(orderService.getOrderById(orderId)).thenReturn(response);

        OrderResponseDto result = orderController.getOrder(orderId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(orderId, result.orderId());
        Mockito.verify(orderService).getOrderById(orderId);
    }

    @Test
    void methodShouldGetOrdersByUserTest() {
        OrderResponseDto orderResponse = OrderTestData.getOrderResponseDto();
        List<OrderResponseDto> orders = List.of(orderResponse);
        UUID userId = OrderTestData.USER_ID;

        Mockito.when(orderService.getOrdersByUserId(userId)).thenReturn(orders);

        List<OrderResponseDto> result = orderController.getOrdersByUser(userId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(orderResponse.orderId(), result.get(0).orderId());
        Mockito.verify(orderService).getOrdersByUserId(userId);
    }

    @Test
    void methodShouldUpdateOrderStatusTest() {
        OrderResponseDto response = OrderTestData.getOrderResponseDto();
        UUID orderId = OrderTestData.ORDER_ID;
        OrderStatus newStatus = OrderStatus.DELIVERED;

        Mockito.when(orderService.updateOrderStatusById(orderId, newStatus))
                .thenReturn(response);

        OrderResponseDto result = orderController.updateOrderStatus(orderId, newStatus);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(response.orderId(), result.orderId());
        Mockito.verify(orderService).updateOrderStatusById(orderId, newStatus);
    }

    @Test
    void methodShouldtestDeleteOrderTest() {
        UUID orderId = OrderTestData.ORDER_ID;

        orderController.deleteOrder(orderId);

        Mockito.verify(orderService).deleteOrderById(orderId);
    }
}