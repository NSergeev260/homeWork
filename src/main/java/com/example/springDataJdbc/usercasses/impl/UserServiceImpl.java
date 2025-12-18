package com.example.springDataJdbc.usercasses.impl;

import com.example.springDataJdbc.persistence.model.UserData;
import com.example.springDataJdbc.persistence.repository.UserRepository;
import com.example.springDataJdbc.usercasses.UserService;
import com.example.springDataJdbc.usercasses.dto.UserRequestDto;
import com.example.springDataJdbc.usercasses.dto.UserResponseDto;
import com.example.springDataJdbc.usercasses.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final UserMapper userMapper;

    @Transactional
    @Override
    public UserResponseDto insertUser(UserRequestDto userRequestDto) {
        UserData userData = userMapper.fromDtoToData(userRequestDto);
        UserData savedUser = userRepo.insertUser(userData);

        log.info("The user with id {} has been ADDED. Time: {}"
                , savedUser.getId(), LocalDateTime.now());

        return userMapper.fromDataToDto(savedUser);
    }

    @Transactional(readOnly = true)
    @Override
    public UserResponseDto findUserById(UUID id) {
        UserData userData = getByID(id);

        log.info("The user with id {} FOUND. Time: {}"
                , id, LocalDateTime.now());

        return userMapper.fromDataToDto(userData);
    }

    @Transactional(readOnly = true)
    @Override
    public UserResponseDto findUserByEmail(String email) {
        UserData userData = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email not found. FAIL! email: " + email));

        log.info("The user with the email {} FOUND. Time: {}"
                , email, LocalDateTime.now());

        return userMapper.fromDataToDto(userData);
    }

    @Transactional
    @Override
    public UserResponseDto updateUser(UUID id, UserRequestDto userRequestDto) {
        UserData userData = getByID(id);
        userData.setName(userRequestDto.name());
        userData.setEmail(userRequestDto.email());
        userData.setProvider(userRequestDto.provider());
        userData.setProviderId(userRequestDto.providerId());
        userData.setRole(userRequestDto.role());
        UserData updatedUser = userRepo.updateUser(userData);

        log.info("The user with the id {} has been UPDATED, Date {}",
                updatedUser.getId(), LocalDateTime.now());

        return userMapper.fromDataToDto(updatedUser);
    }

    @Transactional
    @Override
    public void deleteUser(UUID id) {
        getByID(id);
        userRepo.deleteUser(id);

        log.info("The user with the id {} has been DELETED, Date {}"
                , id, LocalDateTime.now());
    }

    private UserData getByID(UUID id) {

        return userRepo.findUserById(id)
                .orElseThrow(() -> new RuntimeException("Book not found. FAIL! id: " + id));
    }
}
