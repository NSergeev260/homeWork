package com.example.springDataJdbc.usercasses.impl;

import com.example.springDataJdbc.api.exeption.UserAlreadyExistsException;
import com.example.springDataJdbc.persistence.model.UserData;
import com.example.springDataJdbc.persistence.model.UserRole;
import com.example.springDataJdbc.persistence.repository.UserRepository;
import com.example.springDataJdbc.usercasses.UserService;
import com.example.springDataJdbc.usercasses.dto.UserRequestDto;
import com.example.springDataJdbc.usercasses.dto.UserResponseDto;
import com.example.springDataJdbc.usercasses.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final UserMapper mapper;

    @Transactional
    @Override
    public UserResponseDto create(UserRequestDto userRequestDto) {
        if (userRepo.findByEmail(userRequestDto.email()) != null) {
            log.debug("User with email {} already exists", userRequestDto.email());
            throw new UserAlreadyExistsException("User with email " + userRequestDto.email() + " already exists");
        }
        UserData userData = new UserData();
        userData.setEmail(userRequestDto.email());
        userData.setName(userRequestDto.name());
        userData.setRole(UserRole.USER);
        userData.setProviderId(userRequestDto.providerId());
        userData.setProvider(userRequestDto.provider());
        log.debug("Saving user to database: {}", userData);
        UserData userSaved = userRepo.save(userData);
        return mapper.fromDataToDto(userSaved);
    }

}
