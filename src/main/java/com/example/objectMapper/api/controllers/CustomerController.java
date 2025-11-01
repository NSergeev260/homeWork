package com.example.objectMapper.api.controllers;

import com.example.objectMapper.usercasses.CustomerService;
import com.example.objectMapper.usercasses.dto.CustomerRequestDto;
import com.example.objectMapper.usercasses.dto.CustomerResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final ObjectMapper objectMapper;

    @PostMapping
    public ResponseEntity<String> addCustomer(
            @RequestBody String customerJson) throws JsonProcessingException {

        CustomerRequestDto requestDto = objectMapper.readValue(
                customerJson, CustomerRequestDto.class);
        CustomerResponseDto responseDto = customerService.addCustomer(requestDto);
        String responseJson = objectMapper.writeValueAsString(responseDto);

        return ResponseEntity.
                status(HttpStatus.CREATED).
                body(responseJson);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<String> getCustomer(@PathVariable UUID customerId) throws JsonProcessingException {
        CustomerResponseDto customer = customerService.getCustomer(customerId);
        String customerJson = objectMapper.writeValueAsString(customer);

        return ResponseEntity.
                ok(customerJson);
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
