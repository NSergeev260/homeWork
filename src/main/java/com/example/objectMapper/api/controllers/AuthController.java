package com.example.objectMapper.api.controllers;

import com.example.objectMapper.security.AuthService;
import com.example.objectMapper.security.dto.AuthenticatedUserResponseDto;
import com.example.objectMapper.security.dto.LoginRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticatedUserResponseDto> login(
            @RequestBody LoginRequestDto loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @GetMapping("/me")
    public ResponseEntity<String> getCurrentUser() {
        String username = authService.getUsername();
        return ResponseEntity.ok("Current user: " + username);
    }
}