package com.example.objectMapper.usercasses.impl;

import com.example.objectMapper.persistence.repository.CustomerRepository;
import com.example.objectMapper.usercasses.CustomerService;
import com.example.objectMapper.usercasses.dto.CustomerResponseDto;
import com.example.objectMapper.usercasses.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepo;

    @Override
    public CustomerResponseDto addCustomer(CustomerResponseDto customerResponseDto) {
        return null;
    }

    @Override
    public CustomerResponseDto getCustomer(UUID customerID) {
        return null;
    }

    @Override
    public void deleteCustomer(UUID customerID) {

    }
}
