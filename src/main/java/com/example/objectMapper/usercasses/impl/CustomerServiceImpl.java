package com.example.objectMapper.usercasses.impl;

import com.example.objectMapper.api.exeption.BadRequestException;
import com.example.objectMapper.api.exeption.NotFoundException;
import com.example.objectMapper.persistence.model.CustomerEntity;
import com.example.objectMapper.persistence.repository.CustomerRepository;
import com.example.objectMapper.usercasses.CustomerService;
import com.example.objectMapper.usercasses.dto.CustomerRequestDto;
import com.example.objectMapper.usercasses.dto.CustomerResponseDto;
import com.example.objectMapper.usercasses.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepo;

    @Transactional
    @Override
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) {
        String email = customerRequestDto.email();

        if (customerRepo.findByEmail(email).isPresent()) {
            throw new BadRequestException("Customer with email already EXIST. FAIL! email: " + email);
        }

//        CustomerEntity customerEntity = CustomerEntity.builder()
//                .withFirstName(customerRequestDto.firstName())
//                .withLastName(customerRequestDto.lastName())
//                .withEmail(customerRequestDto.email())
//                .withContactNumber(customerRequestDto.contactNumber())
//                .build();
        CustomerEntity customerEntity = customerMapper.fromDtoToEntity(customerRequestDto);
        CustomerEntity addedCustomer = customerRepo.save(customerEntity);

        log.info("The customer with the id {} has been ADDED. Time: {}",
                addedCustomer.getCustomerId(), LocalDateTime.now());

        return customerMapper.fromEntityToDto(addedCustomer);
    }

    @Transactional(readOnly = true)
    @Override
    public CustomerResponseDto getCustomer(UUID customerID) {
        CustomerEntity customerEntity = getCustomerRepoByID(customerID);
        CustomerResponseDto customerResponseDto = customerMapper.fromEntityToDto(customerEntity);

        log.info("The customer with the id {} FOUND. Time: {}"
                , customerID, LocalDateTime.now());

        return customerResponseDto;
    }

    @Transactional
    @Override
    public void deleteCustomer(UUID customerID) {
        CustomerEntity customerEntity = getCustomerRepoByID(customerID);
        customerRepo.delete(customerEntity);

        log.info("The customer with the id {} has been DELETED, Date {}"
                , customerID, LocalDateTime.now());
    }

    private CustomerEntity getCustomerRepoByID(UUID customerId) {

        return customerRepo.findById(customerId)
                .orElseThrow(() ->
                        new NotFoundException("Customer not found. FAIL! ID: " + customerId));
    }
}
