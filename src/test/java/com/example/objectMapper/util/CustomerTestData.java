package com.example.objectMapper.util;

import com.example.objectMapper.persistence.model.CustomerEntity;
import com.example.objectMapper.usercasses.dto.CustomerRequestDto;
import com.example.objectMapper.usercasses.dto.CustomerResponseDto;

import java.util.UUID;

public class CustomerTestData {

    public static final UUID CUSTOMER_ID = UUID.fromString("c1d2e3f4-a5b6-7890-cdef-123456789abc");
    public static final String FIRST_NAME = "Иван";
    public static final String LAST_NAME = "Петров";
    public static final String EMAIL = "ivan.petrov@gmail.com";
    public static final String CONTACT_NUMBER = "+7-999-123-45-67";

    public static CustomerEntity getCustomerEntity() {
        return CustomerEntity.builder()
                .withCustomerId(CUSTOMER_ID)
                .withFirstName(FIRST_NAME)
                .withLastName(LAST_NAME)
                .withEmail(EMAIL)
                .withContactNumber(CONTACT_NUMBER)
                .build();
    }

    public static CustomerRequestDto getCustomerRequestDto() {
        return CustomerRequestDto.builder()
                .withFirstName(FIRST_NAME)
                .withLastName(LAST_NAME)
                .withEmail(EMAIL)
                .withContactNumber(CONTACT_NUMBER)
                .build();
    }

    public static CustomerResponseDto getCustomerResponseDto() {
        return CustomerResponseDto.builder()
                .withCustomerId(CUSTOMER_ID)
                .withFirstName(FIRST_NAME)
                .withLastName(LAST_NAME)
                .withEmail(EMAIL)
                .withContactNumber(CONTACT_NUMBER)
                .build();
    }
}