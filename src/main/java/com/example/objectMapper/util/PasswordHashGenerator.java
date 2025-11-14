package com.example.objectMapper.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHashGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        String password = "1234";
        String hashedPassword = encoder.encode(password);
        
        System.out.println("=== NEW BCrypt HASH ===");
        System.out.println("Password: " + password);
        System.out.println("BCrypt Hash: " + hashedPassword);
        System.out.println("Verification: " + encoder.matches(password, hashedPassword));
    }
}