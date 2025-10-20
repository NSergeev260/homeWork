package com.example.jsonView.usercasses.impl;

import com.example.jsonView.api.exeption.BadRequestException;
import com.example.jsonView.persistence.model.UserEntity;
import com.example.jsonView.persistence.repository.UserRepository;
import com.example.jsonView.usercasses.UserService;
import com.example.jsonView.usercasses.dto.UserRequestDto;
import com.example.jsonView.usercasses.dto.UserResponseDto;
import com.example.jsonView.usercasses.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto addUser(String userName, String userSurname, String userEmail) {

        if (userRepo.findByUserEmail(userEmail).isPresent()) {
            log.info("User with email {} already exists. FAIL! Time: {}", userEmail, LocalDateTime.now());
            throw new BadRequestException("User with this email already exists. FAIL!");
        }

        UserRequestDto userRequestDto = UserRequestDto.builder().
                withUserName(userName).
                withUserSurname(userSurname).
                withUserEmail(userEmail).
                build();

        UserEntity userEntity = userMapper.fromDtoToEntity(userRequestDto);
        UserEntity savedUser = userRepo.save(userEntity);

        log.info("New user with id {} was INSERT, Time: {}", savedUser.getUserId(), LocalDateTime.now());

        return userMapper.fromEntityToDto(savedUser);
    }

    @Override
    public UserResponseDto getUserById(UUID userId) {
        UserEntity userEntity = getUserRepoByID(userId);

        log.info("User with id {} was found. Time: {}", userId, LocalDateTime.now());

        return userMapper.fromEntityToDto(userEntity);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<UserEntity> userEntities = userRepo.findAll();

        return userMapper.fromEntityListToDtoList(userEntities);
    }

    @Override
    public UserResponseDto updateUserById(UUID userId, String userName, String userSurname, String userEmail) {
        UserEntity userEntityUpdated = getUserRepoByID(userId);
        userEntityUpdated.setUserName(userName);
        userEntityUpdated.setUserSurname(userSurname);
        userEntityUpdated.setUserEmail(userEmail);

        UserEntity updatedUser = userRepo.save(userEntityUpdated);

        log.info("User with id {} was UPDATE, Date: {}", userId, LocalDateTime.now());

        return userMapper.fromEntityToDto(updatedUser);
    }


    @Override
    public void deleteUserById(UUID userId) {
        UserEntity userEntity = getUserRepoByID(userId);
        userRepo.delete(userEntity);

        log.info("User with id {} was DELETE, Date: {}", userId, LocalDateTime.now());
    }

    private UserEntity getUserRepoByID(UUID userId) {
        return userRepo.findById(userId)
                .orElseThrow(() ->
                        new BadRequestException("User not exists. FAIL! ID: " + userId));
    }
}
