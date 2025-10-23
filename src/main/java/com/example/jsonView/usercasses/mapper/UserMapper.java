package com.example.jsonView.usercasses.mapper;

import com.example.jsonView.persistence.model.UserEntity;
import com.example.jsonView.usercasses.dto.UserRequestDto;
import com.example.jsonView.usercasses.dto.UserResponseDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
public interface UserMapper {

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "ordersList", ignore = true)
    UserEntity fromDtoToEntity(UserRequestDto userRequestDto);

    UserResponseDto fromEntityToDto(UserEntity userEntity);

    List<UserResponseDto> fromEntityListToDtoList (List<UserEntity> userEntityList);
}
