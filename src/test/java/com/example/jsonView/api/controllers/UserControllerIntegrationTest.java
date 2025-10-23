package com.example.jsonView.api.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.time.YearMonth;
import java.util.List;
import java.util.UUID;

@IntegrationTest
public class UserControllerIntegrationTest {

    private static final String CV_UUID = "123e4567-e89b-12d3-a456-426614174001";
    private static final String URL = "/api/v1/cvs/" + CV_UUID + "/additional-information";

    @Autowired
    private TestRestTemplate restTemplate;

    @SqlGroup({
        @Sql(scripts = "classpath:testdata/clear_additional_information_test_data.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)})
    @Test
    void methodShouldInsertAddInfoTest() {

        AwardDto awardDto = AwardDto.builder().
            withTitle("Title example").
            withDate(YearMonth.of(2020, 2)).
            withIssuer("Issuer example").
            withDescription("Description example").
            withLink("https://javaguru.by/").
            build();

        AdditionalInformationRequestDto addInfoRequestDto = new AdditionalInformationRequestDto(
            "Male",
            "Beard",
            List.of(awardDto));

        HttpEntity<AdditionalInformationRequestDto> addInfoRequestDtoHttpEntity = new HttpEntity<>(addInfoRequestDto);
        ResponseEntity<AdditionalInformationResponseDto> responseEntity =
            restTemplate.exchange(URL,
                HttpMethod.POST,
                addInfoRequestDtoHttpEntity,
                AdditionalInformationResponseDto.class);

        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        AdditionalInformationResponseDto addInfoResponseDto = responseEntity.getBody();
        Assertions.assertNotNull(addInfoResponseDto);

        Assertions.assertEquals(addInfoRequestDto.additionalInfo(), addInfoResponseDto.additionalInfo());
        Assertions.assertEquals(addInfoRequestDto.hobby(), addInfoResponseDto.hobby());
        Assertions.assertEquals(addInfoRequestDto.awards().size(), addInfoResponseDto.awards().size());

        Assertions.assertArrayEquals(addInfoRequestDto.awards().toArray(),
            addInfoResponseDto.awards().toArray());
    }

    @SqlGroup({
        @Sql(scripts = "classpath:testdata/additional_information_test_data.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(scripts = "classpath:testdata/clear_additional_information_test_data.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)})
    @Test
    void methodShouldGetAddInfoTest() {
        AwardDto awardDtoExpected = AwardDto.builder().
            withTitle("Title example").
            withDate(YearMonth.of(2020, 2)).
            withIssuer("Issuer example").
            withDescription("Description example").
            withLink("https://javaguru.by/").
            build();

        AdditionalInformationRequestDto addInfoDtoExpected = new AdditionalInformationRequestDto(
            "Female",
            "Prison",
            List.of(awardDtoExpected));

        ResponseEntity<AdditionalInformationResponseDto> responseEntity =
            restTemplate.exchange(URL,
                HttpMethod.GET,
                null,
                AdditionalInformationResponseDto.class);

        AdditionalInformationResponseDto addInfoResponseDto = responseEntity.getBody();
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertNotNull(addInfoResponseDto);
        Assertions.assertEquals(addInfoDtoExpected.additionalInfo(), addInfoResponseDto.additionalInfo());
        Assertions.assertEquals(addInfoDtoExpected.hobby(), addInfoResponseDto.hobby());

        Assertions.assertArrayEquals(addInfoDtoExpected.awards().toArray(),
            addInfoResponseDto.awards().toArray());
    }

    @Test
    void getShouldReturnNotFound() {
        String uuid = (UUID.randomUUID()).toString();

        ResponseEntity<String> responseEntity =
            restTemplate.exchange("/api/v1/cvs/" + uuid + "/additional-information",
                HttpMethod.GET,
                null,
                String.class);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @SqlGroup({
        @Sql(scripts = "classpath:testdata/additional_information_test_data.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(scripts = "classpath:testdata/clear_additional_information_test_data.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)})
    @Test
    void methodShouldUpdateAddInfoTest() {

        AwardDto awardDto = AwardDto.builder()
            .withTitle("Updated Title")
            .withDate(YearMonth.of(2021, 3))
            .withIssuer("Updated Issuer")
            .withDescription("Updated Description")
            .withLink("https://updatedExample.com/")
            .build();

        AdditionalInformationRequestDto updateRequestDto = new AdditionalInformationRequestDto(
            "Updated Female",
            "Cat",
            List.of(awardDto));

        HttpEntity<AdditionalInformationRequestDto> addInfoRequestDtoHttpEntity = new HttpEntity<>(updateRequestDto);
        ResponseEntity<AdditionalInformationResponseDto> responseEntity =
            restTemplate.exchange(URL,
                HttpMethod.PUT,
                addInfoRequestDtoHttpEntity,
                AdditionalInformationResponseDto.class);

        AdditionalInformationResponseDto addInfoResponseDto = responseEntity.getBody();
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Assertions.assertNotNull(addInfoResponseDto);
        Assertions.assertEquals(updateRequestDto.additionalInfo(), addInfoResponseDto.additionalInfo());
        Assertions.assertEquals(updateRequestDto.hobby(), addInfoResponseDto.hobby());
        Assertions.assertEquals(updateRequestDto.awards().size(), addInfoResponseDto.awards().size());

        Assertions.assertArrayEquals(addInfoResponseDto.awards().toArray(),
            updateRequestDto.awards().toArray());
    }

    @SqlGroup({
        @Sql(scripts = "classpath:testdata/additional_information_test_data.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(scripts = "classpath:testdata/clear_additional_information_test_data.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)})
    @Test
    void methodShouldDeleteAddInfoTest() {

        ResponseEntity<Void> responseEntity =
            restTemplate.exchange(URL,
                HttpMethod.DELETE,
                null,
                Void.class);

        Assertions.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    void deleteShouldReturnNotFound() {
        String uuid = (UUID.randomUUID()).toString();

        ResponseEntity<Void> responseEntity =
            restTemplate.exchange("/api/v1/cvs/" + uuid + "/additional-information",
                HttpMethod.DELETE,
                null,
                Void.class);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}