package com.example.objectMapper.usercasses;

import com.example.objectMapper.usercasses.dto.CustomerRequestDto;
import com.example.objectMapper.usercasses.dto.CustomerResponseDto;

import java.util.UUID;

public interface CustomerService {

    CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto);

    CustomerResponseDto getCustomer(UUID customerID);

    void deleteCustomer(UUID customerID);
}
