package com.example.objectMapper.api.controllers;

import com.example.objectMapper.usercasses.CustomerService;
import com.example.objectMapper.usercasses.dto.CustomerRequestDto;
import com.example.objectMapper.usercasses.dto.CustomerResponseDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/api/customers")
@RestController
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public CustomerResponseDto addCustomer(@Valid @RequestBody CustomerRequestDto customerRequestDto) {
        return customerService.addCustomer(customerRequestDto);
    }

    @GetMapping("/{customerId}")
    public CustomerResponseDto getCustomer(UUID customerID) {
        return customerService.getCustomer(customerID);
    }

    @DeleteMapping("/{customerId}")
    public void deleteCustomer(@PathVariable UUID customerID) {
        customerService.deleteCustomer(customerID);
    }
}
