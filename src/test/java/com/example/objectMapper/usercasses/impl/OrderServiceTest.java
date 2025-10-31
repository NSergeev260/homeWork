package com.example.objectMapper.usercasses.impl;

import com.example.objectMapper.persistence.model.CustomerEntity;
import com.example.objectMapper.persistence.model.OrderEntity;
import com.example.objectMapper.persistence.model.ProductEntity;
import com.example.objectMapper.persistence.repository.CustomerRepository;
import com.example.objectMapper.persistence.repository.OrderRepository;
import com.example.objectMapper.persistence.repository.ProductRepository;
import com.example.objectMapper.usercasses.dto.OrderRequestDto;
import com.example.objectMapper.usercasses.dto.OrderResponseDto;
import com.example.objectMapper.usercasses.dto.OrderStatus;
import com.example.objectMapper.usercasses.mapper.OrderMapper;
import com.example.objectMapper.util.CustomerTestData;
import com.example.objectMapper.util.OrderTestData;
import com.example.objectMapper.util.ProductTestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private OrderRepository orderRepo;

    @Mock
    private CustomerRepository customerRepo;

    @Mock
    private ProductRepository productRepo;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    void methodShouldAddOrderTest() {
        OrderRequestDto requestDto = OrderTestData.getOrderRequestDto();
        CustomerEntity customer = CustomerTestData.getCustomerEntity();
        List<ProductEntity> products = OrderTestData.getSampleProducts();

        OrderEntity savedOrder = OrderTestData.getOrderEntity();
        OrderResponseDto expectedResponse = OrderTestData.getOrderResponseDto();

        Mockito.when(customerRepo.findById(CustomerTestData.CUSTOMER_ID))
                .thenReturn(Optional.of(customer));
        Mockito.when(productRepo.findByNameIn(requestDto.products()))
                .thenReturn(products);
        Mockito.when(orderMapper.fromDtoToEntity(requestDto))
                .thenReturn(savedOrder);
        Mockito.when(orderRepo.save(any(OrderEntity.class)))
                .thenReturn(savedOrder);
        Mockito.when(orderMapper.fromEntityToDto(savedOrder))
                .thenReturn(expectedResponse);

        OrderResponseDto result = orderService.addOrder(requestDto);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(OrderTestData.ORDER_ID, result.orderId());
        Mockito.verify(customerRepo).findById(CustomerTestData.CUSTOMER_ID);
        Mockito.verify(productRepo).findByNameIn(requestDto.products());
        Mockito.verify(orderRepo).save(any(OrderEntity.class));
        Mockito.verify(orderMapper).fromDtoToEntity(requestDto);
        Mockito.verify(orderMapper).fromEntityToDto(savedOrder);
    }

    @Test
    void methodShouldGetOrderTest() {
        OrderEntity order = OrderTestData.getOrderEntity();
        OrderResponseDto expectedResponse = OrderTestData.getOrderResponseDto();

        Mockito.when(orderRepo.findById(OrderTestData.ORDER_ID))
                .thenReturn(Optional.of(order));
        Mockito.when(orderMapper.fromEntityToDto(order))
                .thenReturn(expectedResponse);

        OrderResponseDto result = orderService.getOrder(OrderTestData.ORDER_ID);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(OrderTestData.ORDER_ID, result.orderId());
        Mockito.verify(orderRepo).findById(OrderTestData.ORDER_ID);
        Mockito.verify(orderMapper).fromEntityToDto(order);
    }

    @Test
    void methodShouldUpdateOrderTest() {
        OrderEntity existingOrder = OrderTestData.getOrderEntity();
        OrderRequestDto updateRequest = OrderRequestDto.builder()
                .withCustomerId(CustomerTestData.CUSTOMER_ID)
                .withProducts(Arrays.asList(ProductTestData.PRODUCT_ID.toString()))
                .withOrderDate(LocalDateTime.of
                        (2026, 2, 20, 10, 0))
                .withShippingAddress("г. Санкт-Петербург, Невский пр., д. 10")
                .withOrderStatus(OrderStatus.CONFIRMED)
                .build();

        List<ProductEntity> updatedProducts = Arrays.asList(ProductTestData.getProductEntity());
        OrderEntity updatedOrder = OrderTestData.getOrderEntity();
        updatedOrder.setProducts(updatedProducts);
        updatedOrder.setOrderDate(updateRequest.orderDate());
        updatedOrder.setShippingAddress(updateRequest.shippingAddress());
        updatedOrder.setOrderStatus(updateRequest.orderStatus());

        OrderResponseDto expectedResponse = OrderTestData.getOrderResponseDto();

        Mockito.when(orderRepo.findById(OrderTestData.ORDER_ID))
                .thenReturn(Optional.of(existingOrder));
        Mockito.when(productRepo.findByNameIn(updateRequest.products()))
                .thenReturn(updatedProducts);
        Mockito.when(orderRepo.save(any(OrderEntity.class)))
                .thenReturn(updatedOrder);
        Mockito.when(orderMapper.fromEntityToDto(updatedOrder))
                .thenReturn(expectedResponse);

        OrderResponseDto result = orderService
                .updateOrder(OrderTestData.ORDER_ID, updateRequest);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(OrderTestData.ORDER_ID, result.orderId());
        Mockito.verify(orderRepo).findById(OrderTestData.ORDER_ID);
        Mockito.verify(productRepo).findByNameIn(updateRequest.products());
        Mockito.verify(orderRepo).save(existingOrder);
    }

    @Test
    void methodShouldDeleteOrderTest() {
        OrderEntity order = OrderTestData.getOrderEntity();

        Mockito.when(orderRepo.findById(OrderTestData.ORDER_ID))
                .thenReturn(Optional.of(order));
        Mockito.doNothing().when(orderRepo).delete(order);

        orderService.deleteOrder(OrderTestData.ORDER_ID);

        Mockito.verify(orderRepo).findById(OrderTestData.ORDER_ID);
        Mockito.verify(orderRepo).delete(order);
    }
}