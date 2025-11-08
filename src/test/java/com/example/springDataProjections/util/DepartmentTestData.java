package com.example.springDataProjections.util;

import com.example.springDataProjections.persistence.model.DepartmentEntity;
import com.example.springDataProjections.usercasses.dto.DepartmentRequestDto;
import com.example.springDataProjections.usercasses.dto.DepartmentResponseDto;

import java.util.UUID;

public class DepartmentTestData {

    public static final UUID DEPARTMENT_ID =
            UUID.fromString("cdef0123-4567-4990-1234-567890123456");
    public static final String DEPARTMENT_NAME = "IT";

    public static DepartmentEntity getDepartmentEntity() {
        return DepartmentEntity.builder()
                .withId(DEPARTMENT_ID)
                .withName(DEPARTMENT_NAME)
                .build();
    }

    public static DepartmentRequestDto getDepartmentRequestDto() {
        return DepartmentRequestDto.builder()
                .withName(DEPARTMENT_NAME)
                .build();
    }

    public static DepartmentResponseDto getDepartmentResponseDto() {
        return DepartmentResponseDto.builder()
                .withId(DEPARTMENT_ID)
                .withName(DEPARTMENT_NAME)
                .build();
    }
}