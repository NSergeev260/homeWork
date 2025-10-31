package com.example.objectMapper.usercasses.mapper;

import com.example.objectMapper.persistence.model.OrderEntity;
import com.example.objectMapper.usercasses.dto.OrderRequestDto;
import com.example.objectMapper.usercasses.dto.OrderResponseDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
public interface OrderMapper {

    @Mapping(target = "orderId", ignore = true)
    OrderEntity fromDtoToEntity(OrderRequestDto orderRequestDto);

    OrderResponseDto fromEntityToDto(OrderEntity orderEntity);
}
