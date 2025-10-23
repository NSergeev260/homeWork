package com.example.jsonView.api.controllers;

import com.example.jsonView.api.PostgresTestContainerExtension;
import com.example.jsonView.usercasses.dto.UserRequestDto;
import com.example.jsonView.usercasses.dto.UserResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(PostgresTestContainerExtension.class)
public class UserControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private static final UUID EXISTING_USER_ID =
            UUID.fromString("550e8400-e29b-41d4-a716-446655440001");
    private static final String USERS_URL = "/api/users";
    private static final String USER_BY_ID_URL_TEMPLATE = USERS_URL + "/{userId}";

    @SqlGroup({
        @Sql(scripts = "classpath:testdata/add_json_view_test_data.sql",
                executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(scripts = "classpath:testdata/clear_json_view_test_data.sql",
                executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    })
    @Test
    void methodShouldReturn200AndJsonUserResponseDtoWhenGetUserTest() {
        ResponseEntity<UserResponseDto> response = restTemplate.exchange(
            USER_BY_ID_URL_TEMPLATE,
            HttpMethod.GET,
            null,
            UserResponseDto.class,
            EXISTING_USER_ID
        );

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
    }

    @SqlGroup({
        @Sql(scripts = "classpath:testdata/add_json_view_test_data.sql",
                executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(scripts = "classpath:testdata/clear_json_view_test_data.sql",
                executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    })
    @Test
    void methodShouldReturn200AndJsonUserResponseDtoListWhenGetAllUsersTest() {
        ResponseEntity<UserResponseDto[]> response = restTemplate.exchange(
            USERS_URL,
            HttpMethod.GET,
            null,
            UserResponseDto[].class
        );

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
    }

    @SqlGroup({
        @Sql(scripts = "classpath:testdata/add_json_view_test_data.sql",
                executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(scripts = "classpath:testdata/clear_json_view_test_data.sql",
                executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    })
    @Test
    void methodShouldReturn204WhenDeleteUserTest() {
        ResponseEntity<Void> response = restTemplate.exchange(
            USER_BY_ID_URL_TEMPLATE,
            HttpMethod.DELETE,
            null,
            Void.class,
            EXISTING_USER_ID
        );

        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @SqlGroup({
        @Sql(scripts = "classpath:testdata/add_json_view_test_data.sql",
                executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(scripts = "classpath:testdata/clear_json_view_test_data.sql",
                executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    })
    @Test
    void methodShouldReturn200AndJsonUserResponseDtoWhenUpdateUserTest() {
        UserRequestDto userRequestDto = UserRequestDto.builder()
            .withUserName("ИванОбновленный")
            .withUserSurname("Иванов")
            .withUserEmail("ivan.updated@example.com")
            .build();
        HttpEntity<UserRequestDto> request = new HttpEntity<>(userRequestDto);

        ResponseEntity<UserResponseDto> response = restTemplate.exchange(
            USER_BY_ID_URL_TEMPLATE,
            HttpMethod.PUT,
            request,
            UserResponseDto.class,
            EXISTING_USER_ID
        );

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
    }

    @Test
    void methodShouldReturn201AndJsonUserResponseDtoWhenSaveUserTest() {
        UserRequestDto userRequestDto = UserRequestDto.builder()
            .withUserName("Анна")
            .withUserSurname("Тестова")
            .withUserEmail("anna.test@example.com")
            .build();
        HttpEntity<UserRequestDto> request = new HttpEntity<>(userRequestDto);

        ResponseEntity<UserResponseDto> response = restTemplate.exchange(
            USERS_URL,
            HttpMethod.POST,
            request,
            UserResponseDto.class
        );

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
    }

    @Test
    void methodShouldReturn404WhenGetNonExistentUserTest() {
        UUID nonExistentUserId = UUID.randomUUID();
        
        ResponseEntity<String> response = restTemplate.exchange(
            USER_BY_ID_URL_TEMPLATE,
            HttpMethod.GET,
            null,
            String.class,
            nonExistentUserId
        );

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}