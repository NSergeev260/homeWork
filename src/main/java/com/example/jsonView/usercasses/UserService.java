package com.example.jsonView.usercasses;

import com.example.jsonView.usercasses.dto.UserRequestDto;
import com.example.jsonView.usercasses.dto.UserResponseDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponseDto addUser(UserRequestDto userRequestDto);

    UserResponseDto getUserById(UUID userId);

    List<UserResponseDto> getAllUsers();

    UserResponseDto updateUserById(UUID userId, UserRequestDto userRequestDto);

    void deleteUserById(UUID userId);
}

// Получения списка всех пользователей (без деталей заказов).
// Получения информации о конкретном пользователе (включая детали заказов).
// Создания нового пользователя.
// Обновления информации о пользователе.
// Удаления пользователя.