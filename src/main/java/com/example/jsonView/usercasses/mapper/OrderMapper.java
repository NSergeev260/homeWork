package com.example.jsonView.usercasses.mapper;

import com.example.jsonView.persistence.model.OrderEntity;
import com.example.jsonView.usercasses.dto.OrderRequestDto;
import com.example.jsonView.usercasses.dto.OrderResponseDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
public interface OrderMapper {

    OrderEntity fromDtoToEntity(OrderRequestDto OrderRequestDto);

    OrderResponseDto fromEntityToDto(OrderEntity OrderEntity);

    List<OrderResponseDto> fromEntityListToDtoList (List<OrderEntity> userEntityList);
}
