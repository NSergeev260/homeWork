package com.example.springDataJdbc.usercasses;

import com.example.springDataJdbc.usercasses.dto.UserRequestDto;
import com.example.springDataJdbc.usercasses.dto.UserResponseDto;

import java.util.UUID;

public interface UserService {

    UserResponseDto insertUser(UserRequestDto userRequestDto);

    UserResponseDto findUserByEmail(String email);

    UserResponseDto updateUser(UUID id, UserRequestDto userRequestDto);

    void deleteUser(UUID id);
}
