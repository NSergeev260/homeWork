package com.example.jsonView.usercasses.mapper;

import com.example.jsonView.persistence.model.OrderEntity;
import com.example.jsonView.usercasses.dto.OrderRequestDto;
import com.example.jsonView.usercasses.dto.OrderResponseDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true),
        uses = {ProductMapper.class})
public interface OrderMapper {

    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "productsList", source = "productsList")
    OrderEntity fromDtoToEntity(OrderRequestDto OrderRequestDto);

    OrderResponseDto fromEntityToDto(OrderEntity OrderEntity);

    List<OrderResponseDto> fromEntityListToDtoList (List<OrderEntity> userEntityList);
}
