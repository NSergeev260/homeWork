package com.example.jsonView.api.controllers;

import com.example.jsonView.api.util.UserTestData;
import com.example.jsonView.usercasses.UserService;
import com.example.jsonView.usercasses.dto.UserRequestDto;
import com.example.jsonView.usercasses.dto.UserResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void methodShouldAddUserTest() {
        UserRequestDto request = UserTestData.getUserRequestDto().build();
        UserResponseDto response = UserTestData.getUserResponseDto().build();

        Mockito.when(userService.addUser(request)).thenReturn(response);

        UserResponseDto result = userController.addUser(request);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(response.userId(), result.userId());
        Mockito.verify(userService).addUser(request);
    }

    @Test
    void methodShouldGetUserTest() {
        UserResponseDto response = UserTestData.getUserResponseDto().build();
        UUID userId = UserTestData.USER_ID;

        Mockito.when(userService.getUserById(userId)).thenReturn(response);

        UserResponseDto result = userController.getUser(userId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(userId, result.userId());
        Mockito.verify(userService).getUserById(userId);
    }

    @Test
    void methodShouldGetAllUsersTest() {
        UserResponseDto userResponse = UserTestData.getUserResponseDto().build();
        List<UserResponseDto> users = List.of(userResponse);

        Mockito.when(userService.getAllUsers()).thenReturn(users);

        List<UserResponseDto> result = userController.getAllUsers();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(userResponse.userId(), result.get(0).userId());
        Mockito.verify(userService).getAllUsers();
    }

    @Test
    void methodShouldUpdateUserTest() {
        UserRequestDto request = UserTestData.getUserRequestDto().build();
        UserResponseDto response = UserTestData.getUserResponseDto().build();
        UUID userId = UserTestData.USER_ID;

        Mockito.when(userService.updateUserById(userId, request)).thenReturn(response);

        UserResponseDto result = userController.updateUser(userId, request);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(response.userId(), result.userId());
        Mockito.verify(userService).updateUserById(userId, request);
    }

    @Test
    void methodShouldDeleteUserTest() {
        UUID userId = UserTestData.USER_ID;

        userController.deleteUser(userId);

        Mockito.verify(userService).deleteUserById(userId);
    }
}