package com.example.springDataProjections.usercasses.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder(setterPrefix = "with")
public record DepartmentRequestDto(

        @NotBlank(message = "The name of department cannot be empty or null")
        String name) {
}
