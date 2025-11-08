package com.example.springDataProjections.usercasses.dto;


import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder(setterPrefix = "with")
public record EmployeeResponseDto(
        UUID id,
        String firstName,
        String lastName,
        String position,
        BigDecimal salary,
        String departmentName) {
}
