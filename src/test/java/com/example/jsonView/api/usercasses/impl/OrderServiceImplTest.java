package com.example.jsonView.api.usercasses.impl;

import com.example.jsonView.api.exeption.NotFoundException;
import com.example.jsonView.api.util.OrderTestData;
import com.example.jsonView.api.util.UserTestData;
import com.example.jsonView.persistence.model.OrderEntity;
import com.example.jsonView.persistence.model.UserEntity;
import com.example.jsonView.persistence.repository.OrderRepository;
import com.example.jsonView.persistence.repository.UserRepository;
import com.example.jsonView.usercasses.dto.OrderRequestDto;
import com.example.jsonView.usercasses.dto.OrderResponseDto;
import com.example.jsonView.usercasses.dto.OrderStatus;
import com.example.jsonView.usercasses.impl.OrderServiceImpl;
import com.example.jsonView.usercasses.mapper.OrderMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    private OrderRequestDto orderRequestDto;
    private OrderResponseDto orderResponseDto;
    private UserEntity userEntity;
    private OrderEntity orderEntity;

    @BeforeEach
    void setUp() {
        orderRequestDto = OrderTestData.getOrderRequestDto();
        orderResponseDto = OrderTestData.getOrderResponseDto();
        userEntity = UserTestData.getUserEntity().build();
        orderEntity = OrderTestData.getOrderEntity(userEntity);
    }

    @Test
    void  methodShouldAddOrderTest() {
        Mockito.when(userRepository.findById(OrderTestData.USER_ID))
                .thenReturn(Optional.of(userEntity));
        Mockito.when(orderMapper.fromDtoToEntity(orderRequestDto))
                .thenReturn(orderEntity);
        Mockito.when(orderRepository.save(orderEntity))
                .thenReturn(orderEntity);
        Mockito.when(orderMapper.fromEntityToDto(orderEntity))
                .thenReturn(orderResponseDto);

        OrderResponseDto result = orderService.addOrder(orderRequestDto);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(OrderTestData.ORDER_ID, result.orderId());
    }

    @Test
    void methodAddOrderWhenUserNotFoundShouldThrowExceptionTest() {
        Mockito.when(userRepository.findById(OrderTestData.USER_ID))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () ->
                orderService.addOrder(orderRequestDto));
    }

    @Test
    void methodShouldGetOrderByIdTest() {
        Mockito.when(orderRepository.findById(OrderTestData.ORDER_ID)).
                thenReturn(Optional.of(orderEntity));
        Mockito.when(orderMapper.fromEntityToDto(orderEntity)).
                thenReturn(orderResponseDto);

        OrderResponseDto result = orderService.getOrderById(OrderTestData.ORDER_ID);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(OrderTestData.ORDER_ID, result.orderId());
    }

    @Test
    void methodShouldGetOrdersByUserIdTest() {
        List<OrderEntity> orderList = List.of(orderEntity);
        List<OrderResponseDto> responseList = List.of(orderResponseDto);

        Mockito.when(userRepository.findById(OrderTestData.USER_ID)).
                thenReturn(Optional.of(userEntity));
        Mockito.when(orderRepository.findByUserUserId(OrderTestData.USER_ID))
                .thenReturn(orderList);
        Mockito.when(orderMapper.fromEntityListToDtoList(orderList))
                .thenReturn(responseList);

        List<OrderResponseDto> result = orderService.getOrdersByUserId(OrderTestData.USER_ID);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
    }

    @Test
    void methodShouldUpdateOrderStatusTest() {
        Mockito.when(orderRepository.findById(OrderTestData.ORDER_ID))
                .thenReturn(Optional.of(orderEntity));
        Mockito.when(orderRepository.save(orderEntity))
                .thenReturn(orderEntity);
        Mockito.when(orderMapper.fromEntityToDto(orderEntity))
                .thenReturn(orderResponseDto);

        OrderResponseDto result = orderService.updateOrderStatusById(
                OrderTestData.ORDER_ID, OrderStatus.DELIVERED);

        Assertions.assertNotNull(result);
    }

    @Test
    void methodShouldDeleteOrderTest() {
        Mockito.when(orderRepository.findById(OrderTestData.ORDER_ID))
                .thenReturn(Optional.of(orderEntity));
        Mockito.doNothing().when(orderRepository).delete(orderEntity);

        orderService.deleteOrderById(OrderTestData.ORDER_ID);

        Mockito.verify(orderRepository).delete(orderEntity);
    }
}