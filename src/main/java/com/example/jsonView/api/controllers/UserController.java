package com.example.jsonView.api.controllers;

import com.example.jsonView.api.view.Views;
import com.example.jsonView.usercasses.UserService;
import com.example.jsonView.usercasses.dto.OrderStatus;
import com.example.jsonView.usercasses.dto.UserRequestDto;
import com.example.jsonView.usercasses.dto.UserResponseDto;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/{userId}")
    public UserResponseDto addUser(String name, String surname, String email) {
        return userService.addUser(name, surname, email);
    }

    @JsonView(Views.UserDetails.class)
    @GetMapping
    public UserResponseDto getUser(UUID userId) {
        return userService.getUserById(userId);
    }

    @JsonView(Views.UserSummary.class)
    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/{userId}")
    public UserResponseDto updateUser(UUID userId, String name, String surname, String email) {
        return userService.updateUserById(userId, name, surname, email);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(UUID userId) {
        userService.deleteUserById(userId);
    }

}
