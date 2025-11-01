package com.example.objectMapper.controllers;

import com.example.objectMapper.api.controllers.OrderController;
import com.example.objectMapper.usercasses.OrderService;
import com.example.objectMapper.usercasses.dto.OrderRequestDto;
import com.example.objectMapper.usercasses.dto.OrderResponseDto;
import com.example.objectMapper.util.OrderTestData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private OrderController orderController;

    @Test
    void methodShouldAddOrderTest() throws JsonProcessingException {
        OrderRequestDto request = OrderTestData.getOrderRequestDto();
        OrderResponseDto response = OrderTestData.getOrderResponseDto();
        String requestJson = "{\"customerId\":\"123\",\"products\":[\"product1\"]}";
        String responseJson = "{\"orderId\":\"456\",\"shippingAddress\":\"test address\"}";

        Mockito.when(objectMapper.readValue(requestJson, OrderRequestDto.class))
                .thenReturn(request);
        Mockito.when(orderService.addOrder(request))
                .thenReturn(response);
        Mockito.when(objectMapper.writeValueAsString(response))
                .thenReturn(responseJson);

        ResponseEntity<String> result = orderController.addOrder(requestJson);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.CREATED, result.getStatusCode());
        Assertions.assertEquals(responseJson, result.getBody());
        Mockito.verify(orderService).addOrder(request);
    }

    @Test
    void methodShouldGetOrderByIdTest() throws JsonProcessingException {
        OrderResponseDto response = OrderTestData.getOrderResponseDto();
        UUID orderId = OrderTestData.ORDER_ID;
        String responseJson = "{\"orderId\":\"456\",\"shippingAddress\":\"test address\"}";

        Mockito.when(orderService.getOrder(orderId))
                .thenReturn(response);
        Mockito.when(objectMapper.writeValueAsString(response))
                .thenReturn(responseJson);

        ResponseEntity<String> result = orderController.getOrder(orderId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(responseJson, result.getBody());
        Mockito.verify(orderService).getOrder(orderId);
    }

    @Test
    void methodShouldUpdateOrderTest() throws JsonProcessingException {
        OrderRequestDto request = OrderTestData.getOrderRequestDto();
        OrderResponseDto response = OrderTestData.getOrderResponseDto();
        UUID orderId = OrderTestData.ORDER_ID;
        String requestJson = "{\"customerId\":\"123\",\"products\":[\"product1\"]}";
        String responseJson = "{\"orderId\":\"456\",\"shippingAddress\":\"updated address\"}";

        Mockito.when(objectMapper.readValue(requestJson, OrderRequestDto.class))
                .thenReturn(request);
        Mockito.when(orderService.updateOrder(orderId, request))
                .thenReturn(response);
        Mockito.when(objectMapper.writeValueAsString(response))
                .thenReturn(responseJson);

        ResponseEntity<String> result = orderController.updateOrder(orderId, requestJson);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(responseJson, result.getBody());
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