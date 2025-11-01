package com.example.objectMapper.usercasses.impl;

import com.example.objectMapper.api.exeption.NotFoundException;
import com.example.objectMapper.persistence.model.CustomerEntity;
import com.example.objectMapper.persistence.model.OrderEntity;
import com.example.objectMapper.persistence.model.ProductEntity;
import com.example.objectMapper.persistence.repository.CustomerRepository;
import com.example.objectMapper.persistence.repository.OrderRepository;
import com.example.objectMapper.persistence.repository.ProductRepository;
import com.example.objectMapper.usercasses.OrderService;
import com.example.objectMapper.usercasses.dto.OrderRequestDto;
import com.example.objectMapper.usercasses.dto.OrderResponseDto;
import com.example.objectMapper.usercasses.mapper.OrderMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepo;
    private final CustomerRepository customerRepo;
    private final ProductRepository productRepo;

    @Transactional
    @Override
    public OrderResponseDto addOrder(OrderRequestDto orderRequestDto) {
        UUID customerId = orderRequestDto.customerId();

        CustomerEntity customerEntity = customerRepo.findById(customerId)
                .orElseThrow(() ->
                        new NotFoundException("Customer not found. FAIL! ID: " + customerId));

        List<String> productNames = orderRequestDto.products();
        List<ProductEntity> productEntities = productRepo.findByNameIn(productNames);

        if (productEntities.size() != productNames.size()) {
            throw new NotFoundException("Some products not found. Requested: " + productNames);
        }

        BigDecimal totalPrice = calculateTotalPrice(productEntities);

        OrderEntity orderEntity = orderMapper.fromDtoToEntity(orderRequestDto);
        orderEntity.setCustomer(customerEntity);
        orderEntity.setProducts(productEntities);
        orderEntity.setTotalPrice(totalPrice);
        OrderEntity addedOrder = orderRepo.save(orderEntity);

        log.info("The order with the id {} has been ADDED. Time: {}",
                addedOrder.getOrderId(), LocalDateTime.now());

        return orderMapper.fromEntityToDto(addedOrder);
    }

    @Transactional(readOnly = true)
    @Override
    public OrderResponseDto getOrder(UUID orderId) {
        OrderEntity orderEntity = getOrderRepoByID(orderId);
        OrderResponseDto orderResponseDto = orderMapper.fromEntityToDto(orderEntity);

        log.info("The order with the id {} FOUND. Time: {}"
                , orderId, LocalDateTime.now());

        return orderResponseDto;
    }

    @Transactional
    @Override
    public OrderResponseDto updateOrder(UUID orderId, OrderRequestDto orderRequestDto) {
        OrderEntity orderEntity = getOrderRepoByID(orderId);

        List<String> productNames = orderRequestDto.products();
        List<ProductEntity> productEntities = productRepo.findByNameIn(productNames);

        if (productEntities.size() != productNames.size()) {
            throw new NotFoundException("Some products not found. Requested: " + productNames);
        }

        BigDecimal totalPrice = calculateTotalPrice(productEntities);

        orderEntity.setProducts(productEntities);
        orderEntity.setOrderDate(orderRequestDto.orderDate());
        orderEntity.setShippingAddress(orderRequestDto.shippingAddress());
        orderEntity.setTotalPrice(totalPrice);
        orderEntity.setOrderStatus(orderRequestDto.orderStatus());

        OrderEntity updatedOrder = orderRepo.save(orderEntity);

        log.info("The order with the id {} has been UPDATED, Date {}",
                updatedOrder.getOrderId(), LocalDateTime.now());

        return orderMapper.fromEntityToDto(updatedOrder);
    }

    @Transactional
    @Override
    public void deleteOrder(UUID orderId) {
        OrderEntity orderEntity = getOrderRepoByID(orderId);
        orderRepo.delete(orderEntity);

        log.info("The order with the id {} has been DELETED, Date {}"
                , orderId, LocalDateTime.now());
    }

    private OrderEntity getOrderRepoByID(UUID orderId) {

        return orderRepo.findById(orderId)
                .orElseThrow(() ->
                        new NotFoundException("Order not found. FAIL! ID: " + orderId));
    }

    private BigDecimal calculateTotalPrice(List<ProductEntity> products) {
        return products.stream()
                .map(ProductEntity::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
