package com.example.objectMapper.usercasses.mapper;

import com.example.objectMapper.persistence.model.CustomerEntity;
import com.example.objectMapper.usercasses.dto.CustomerRequestDto;
import com.example.objectMapper.usercasses.dto.CustomerResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerResponseDto fromEntityToDto(CustomerEntity customerEntity) {

        if (customerEntity == null) {
            return null;
        }
        
        return CustomerResponseDto.builder()
                .withCustomerId(customerEntity.getCustomerId())
                .withFirstName(customerEntity.getFirstName())
                .withLastName(customerEntity.getLastName())
                .withEmail(customerEntity.getEmail())
                .withContactNumber(customerEntity.getContactNumber())
                .build();
    }

    @Override
    public CustomerEntity fromDtoToEntity(CustomerRequestDto customerRequestDto) {

        if (customerRequestDto == null) {
            return null;
        }
        
        return CustomerEntity.builder()
                .withFirstName(customerRequestDto.firstName())
                .withLastName(customerRequestDto.lastName())
                .withEmail(customerRequestDto.email())
                .withContactNumber(customerRequestDto.contactNumber())
                .build();
    }
}