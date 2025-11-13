package com.example.objectMapper.security;


import com.example.objectMapper.security.dto.AuthenticatedUserResponseDto;
import com.example.objectMapper.security.dto.LoginRequestDto;

public interface AuthService {

    AuthenticatedUserResponseDto login(LoginRequestDto loginRequestDto);

    String getUsername();
}
