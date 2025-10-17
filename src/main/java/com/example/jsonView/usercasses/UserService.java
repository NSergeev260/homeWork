package com.example.jsonView.usercasses;

import com.example.jsonView.usercasses.dto.UserResponseDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponseDto addUser(String userName, String userSurname, String userEmail);

    UserResponseDto getUserById(UUID userId);

    List<UserResponseDto> getAllUser();

    UserResponseDto updateUserById(UUID userId, String userName, String userSurname, String userEmail);

    void deleteUserById(UUID userId);
}

// Получения списка всех пользователей (без деталей заказов).
// Получения информации о конкретном пользователе (включая детали заказов).
// Создания нового пользователя.
// Обновления информации о пользователе.
// Удаления пользователя.