package com.example.springDataJdbc.usercasses;

import com.example.springDataJdbc.usercasses.dto.UserRequestDto;
import com.example.springDataJdbc.usercasses.dto.UserResponseDto;

public interface UserService {

    UserResponseDto create(UserRequestDto dto);
}
