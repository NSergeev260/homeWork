package com.example.jsonView.api.usercasses.impl;

import com.example.jsonView.api.exeption.BadRequestException;
import com.example.jsonView.api.exeption.NotFoundException;
import com.example.jsonView.api.util.UserTestData;
import com.example.jsonView.persistence.model.UserEntity;
import com.example.jsonView.persistence.repository.UserRepository;
import com.example.jsonView.usercasses.dto.UserRequestDto;
import com.example.jsonView.usercasses.dto.UserResponseDto;
import com.example.jsonView.usercasses.impl.UserServiceImpl;
import com.example.jsonView.usercasses.mapper.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    private UserRequestDto userRequestDto;
    private UserEntity userEntity;
    private UserResponseDto userResponseDto;

    @BeforeEach
    void setUp() {
        userRequestDto = UserTestData.getUserRequestDto().build();
        userEntity = UserTestData.getUserEntity().build();
        userResponseDto = UserTestData.getUserResponseDto().build();
    }

    @Test
    void methodShouldAddUserTest() {
        Mockito.when(userRepository.findByUserEmail(UserTestData.USER_EMAIL))
                .thenReturn(Optional.empty());
        Mockito.when(userMapper.fromDtoToEntity(userRequestDto))
                .thenReturn(userEntity);
        Mockito.when(userRepository.save(userEntity))
                .thenReturn(userEntity);
        Mockito.when(userMapper.fromEntityToDto(userEntity))
                .thenReturn(userResponseDto);

        UserResponseDto result = userService.addUser(userRequestDto);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(UserTestData.USER_ID, result.userId());
    }

    @Test
    void methodAddUserWhenEmailExistsShouldThrowExceptionTest() {
        Mockito.when(userRepository.findByUserEmail(UserTestData.USER_EMAIL))
                .thenReturn(Optional.of(userEntity));

        Assertions.assertThrows(BadRequestException.class, () ->
                userService.addUser(userRequestDto));
    }

    @Test
    void methodShouldGetUserByIdTest() {
        Mockito.when(userRepository.findById(UserTestData.USER_ID))
                .thenReturn(Optional.of(userEntity));
        Mockito.when(userMapper.fromEntityToDto(userEntity))
                .thenReturn(userResponseDto);

        UserResponseDto result = userService.getUserById(UserTestData.USER_ID);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(UserTestData.USER_ID, result.userId());
    }

    @Test
    void methodGetUserByIdWhenUserNotFoundShouldThrowExceptionTest() {
        Mockito.when(userRepository.findById(UserTestData.USER_ID))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () ->
                userService.getUserById(UserTestData.USER_ID));
    }

    @Test
    void methodShouldGetAllUsersTest() {
        List<UserEntity> userList = List.of(userEntity);
        List<UserResponseDto> responseList = List.of(userResponseDto);

        Mockito.when(userRepository.findAll())
                .thenReturn(userList);
        Mockito.when(userMapper.fromEntityListToDtoList(userList))
                .thenReturn(responseList);

        List<UserResponseDto> result = userService.getAllUsers();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
    }

    @Test
    void methodShouldUpdateUserByIdTest() {
        Mockito.when(userRepository.findById(UserTestData.USER_ID))
                .thenReturn(Optional.of(userEntity));
        Mockito.when(userRepository.save(userEntity))
                .thenReturn(userEntity);
        Mockito.when(userMapper.fromEntityToDto(userEntity))
                .thenReturn(userResponseDto);

        UserResponseDto result = userService.updateUserById(UserTestData.USER_ID, userRequestDto);

        Assertions.assertNotNull(result);
    }

    @Test
    void methodShouldDeleteUserByIdTest() {
        Mockito.when(userRepository.findById(UserTestData.USER_ID))
                .thenReturn(Optional.of(userEntity));
        Mockito.doNothing().when(userRepository).delete(userEntity);

        userService.deleteUserById(UserTestData.USER_ID);

        Mockito.verify(userRepository).delete(userEntity);
    }
}