package com.example.objectMapper.api.controllers;

import com.example.objectMapper.usercasses.CustomerService;
import com.example.objectMapper.usercasses.dto.CustomerRequestDto;
import com.example.objectMapper.usercasses.dto.CustomerResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/customers")
@RestController
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponseDto> addCustomer(
            @Valid @RequestBody CustomerRequestDto customerRequestDto) {
        CustomerResponseDto response = customerService.addCustomer(customerRequestDto);
        return ResponseEntity.
                status(HttpStatus.CREATED).
                body(response);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponseDto> getCustomer(
            @PathVariable UUID customerId) {
        CustomerResponseDto response = customerService.getCustomer(customerId);
        return ResponseEntity.
                ok(response);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(
            @PathVariable UUID customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.
                noContent().
                build();
    }
}
