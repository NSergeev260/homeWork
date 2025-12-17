package com.example.springDataJdbc.usercasses.mapper;

import com.example.springDataJdbc.persistence.model.UserData;
import com.example.springDataJdbc.usercasses.dto.UserRequestDto;
import com.example.springDataJdbc.usercasses.dto.UserResponseDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
public interface UserMapper {

    UserData fromDtoToData(UserRequestDto userRequestDto);

    UserResponseDto fromDataToDto(UserData userData);

}
