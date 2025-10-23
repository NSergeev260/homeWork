package com.example.jsonView.api.util;

import com.example.jsonView.persistence.model.OrderEntity;
import com.example.jsonView.persistence.model.UserEntity;
import com.example.jsonView.usercasses.dto.OrderResponseDto;
import com.example.jsonView.usercasses.dto.UserRequestDto;
import com.example.jsonView.usercasses.dto.UserResponseDto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserTestData {

    public static final UUID USER_ID = UUID.fromString("323e4567-e89b-12d3-a456-426614174000");
    public static final String USER_NAME = "Michael";
    public static final String USER_SURNAME = "Jordan";
    public static final String USER_EMAIL = "mjordan@gmail.com";
    public static final List<OrderResponseDto> ORDER_RESPONSE_LIST = new ArrayList<>();
    public static final List<OrderEntity> ORDER_ENTITY_LIST = new ArrayList<>();

    private UserTestData() {
    }

    public static UserRequestDto.UserRequestDtoBuilder getUserRequestDto() {

        return UserRequestDto.builder()
                .withUserName(USER_NAME)
                .withUserSurname(USER_SURNAME)
                .withUserEmail(USER_EMAIL);
    }

    public static UserResponseDto.UserResponseDtoBuilder getUserResponseDto() {

        return UserResponseDto.builder()
                .withUserId(USER_ID)
                .withUserName(USER_NAME)
                .withUserSurname(USER_SURNAME)
                .withUserEmail(USER_EMAIL)
                .withOrdersList(ORDER_RESPONSE_LIST);
    }

    public static UserEntity.UserEntityBuilder getUserEntity() {

        return UserEntity.builder()
                .withUserId(USER_ID)
                .withUserName(USER_NAME)
                .withUserSurname(USER_SURNAME)
                .withUserEmail(USER_EMAIL)
                .withOrdersList(ORDER_ENTITY_LIST);
    }
}
