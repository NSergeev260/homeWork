package com.example.springDataProjections.usercasses.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.math.BigDecimal;

@Builder(setterPrefix = "with")
public record EmployeeRequestDto(

        @NotBlank(message = "The name of employee cannot be empty or null")
        String firstName,
        @NotBlank(message = "The last name of employee cannot be empty or null")
        String lastName,
        @NotBlank(message = "The employee`s position cannot be empty or null")
        String position,
        @NotNull(message = "Salary is required")
        @Positive(message = "Salary must be positive")
        BigDecimal salary) {
}
