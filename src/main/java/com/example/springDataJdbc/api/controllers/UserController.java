package com.example.springDataJdbc.api.controllers;

import com.example.springDataJdbc.usercasses.UserService;
import com.example.springDataJdbc.usercasses.dto.UserRequestDto;
import com.example.springDataJdbc.usercasses.dto.UserResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> addUser(
            @Valid @RequestBody UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = userService.insertUser(userRequestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userResponseDto);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUserById(
            @PathVariable UUID userId) {
        UserResponseDto userResponseDto = userService.findUserById(userId);

        return ResponseEntity
                .ok()
                .body(userResponseDto);
    }

    @GetMapping("/search")
    public ResponseEntity<UserResponseDto> getUserByEmail(
            @RequestParam String email) {
        UserResponseDto userResponseDto = userService.findUserByEmail(email);

        return ResponseEntity
                .ok()
                .body(userResponseDto);
    }

    @GetMapping("/user")
    public String user(@AuthenticationPrincipal OAuth2User principal, Model model) {
        model.addAttribute("name", principal.getAttribute("name"));
        model.addAttribute("login", principal.getAttribute("login"));
        model.addAttribute("id", principal.getAttribute("id"));
        model.addAttribute("email", principal.getAttribute("email"));
        return "user";
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable UUID userId, @Valid @RequestBody UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = userService.updateUser(userId, userRequestDto);

        return ResponseEntity
                .ok()
                .body(userResponseDto);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID userId) {
        userService.deleteUser(userId);

        return ResponseEntity
                .noContent()
                .build();
    }
}
