package com.example.objectMapper.usercasses.mapper;

import com.example.objectMapper.persistence.model.OrderEntity;
import com.example.objectMapper.usercasses.dto.CustomerResponseDto;
import com.example.objectMapper.usercasses.dto.OrderRequestDto;
import com.example.objectMapper.usercasses.dto.OrderResponseDto;
import com.example.objectMapper.usercasses.dto.ProductResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapperImpl implements OrderMapper {

    @Autowired
    private CustomerMapper customerMapper;
    
    @Autowired
    private ProductMapper productMapper;

    @Override
    public OrderResponseDto fromEntityToDto(OrderEntity orderEntity) {
        if (orderEntity == null) {
            return null;
        }

        CustomerResponseDto customerDto = null;
        if (orderEntity.getCustomer() != null) {
            customerDto = customerMapper.fromEntityToDto(orderEntity.getCustomer());
        }

        List<ProductResponseDto> productDtos = null;
        if (orderEntity.getProducts() != null) {
            productDtos = orderEntity.getProducts().stream()
                    .map(productMapper::fromEntityToDto)
                    .collect(Collectors.toList());
        }
        
        return OrderResponseDto.builder()
                .withOrderId(orderEntity.getOrderId())
                .withCustomer(customerDto)
                .withProducts(productDtos)
                .withOrderDate(orderEntity.getOrderDate())
                .withShippingAddress(orderEntity.getShippingAddress())
                .withTotalPrice(orderEntity.getTotalPrice())
                .withOrderStatus(orderEntity.getOrderStatus())
                .build();
    }

    @Override
    public OrderEntity fromDtoToEntity(OrderRequestDto orderRequestDto) {

        if (orderRequestDto == null) {
            return null;
        }
        
        return OrderEntity.builder()
                .withOrderDate(orderRequestDto.orderDate())
                .withShippingAddress(orderRequestDto.shippingAddress())
                .withOrderStatus(orderRequestDto.orderStatus())
                .build();
    }
}