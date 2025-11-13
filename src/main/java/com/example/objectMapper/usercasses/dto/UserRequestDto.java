package com.example.objectMapper.usercasses.dto;

import by.javaguru.profiler.usecasses.util.ValidationConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Builder(setterPrefix = "with")
public record UserRequestDto(
        @NotNull
        @Length(max = 60, message = "The email is too long, the max number of symbols is 60")
        @Pattern(regexp = ValidationConstants.REGEXP_VALIDATE_EMAIL,
                message = "Invalid email. Example of the correct variant: example@example.com ")
        @Schema(defaultValue = "Lebowski@gmail.com", description = "Email address")
        @Email
        String email,
        @NotNull
        @Pattern(regexp = ValidationConstants.REGEXP_VALIDATE_PASSWORD,
                message = "Invalid password. Example of the correct variant: Password1! ")
        @Schema(defaultValue = "Password1!", description = "Password")
        String password) {
}
