package com.example.jsonView.api.controllers;

import com.example.jsonView.api.PostgresTestContainerExtension;
import com.example.jsonView.persistence.model.Product;
import com.example.jsonView.usercasses.dto.OrderRequestDto;
import com.example.jsonView.usercasses.dto.OrderResponseDto;
import com.example.jsonView.usercasses.dto.OrderStatus;
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

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(PostgresTestContainerExtension.class)
public class OrderControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private static final UUID EXISTING_USER_ID = UUID.fromString("550e8400-e29b-41d4-a716-446655440001");
    private static final UUID EXISTING_ORDER_ID = UUID.fromString("660e8400-e29b-41d4-a716-446655440001");
    private static final String ORDERS_URL = "/api/orders";
    private static final String ORDER_BY_ID_URL_TEMPLATE = ORDERS_URL + "/{orderId}";
    private static final String ORDERS_BY_USER_URL_TEMPLATE = ORDERS_URL + "/user/{userId}";
    private static final String ORDER_STATUS_URL_TEMPLATE = ORDERS_URL + "/{orderId}/status";

    @SqlGroup({
        @Sql(scripts = "classpath:testdata/add_json_view_test_data.sql",
                executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(scripts = "classpath:testdata/clear_json_view_test_data.sql",
                executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    })
    @Test
    void methodShouldReturn200AndJsonOrderResponseDtoWhenGetOrderTest() {
        ResponseEntity<OrderResponseDto> response = restTemplate.exchange(
            ORDER_BY_ID_URL_TEMPLATE,
            HttpMethod.GET,
            null,
            OrderResponseDto.class,
            EXISTING_ORDER_ID
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
    void methodShouldReturn200AndJsonOrderResponseDtoListWhenGetOrdersByUserTest() {
        ResponseEntity<OrderResponseDto[]> response = restTemplate.exchange(
            ORDERS_BY_USER_URL_TEMPLATE,
            HttpMethod.GET,
            null,
            OrderResponseDto[].class,
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
    void methodShouldReturn200WhenDeleteOrderTest() {
        ResponseEntity<Void> response = restTemplate.exchange(
            ORDER_BY_ID_URL_TEMPLATE,
            HttpMethod.DELETE,
            null,
            Void.class,
            EXISTING_ORDER_ID
        );

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @SqlGroup({
        @Sql(scripts = "classpath:testdata/add_json_view_test_data.sql",
                executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(scripts = "classpath:testdata/clear_json_view_test_data.sql",
                executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    })
    @Test
    void methodShouldReturn200AndJsonOrderResponseDtoWhenUpdateOrderStatusTest() {
        ResponseEntity<OrderResponseDto> response = restTemplate.exchange(
            ORDER_STATUS_URL_TEMPLATE + "?orderStatus=DELIVERED",
            HttpMethod.PUT,
            null,
            OrderResponseDto.class,
            EXISTING_ORDER_ID
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
    void methodShouldReturn201AndJsonOrderResponseDtoWhenSaveOrderTest() {
        OrderRequestDto orderRequestDto = OrderRequestDto.builder()
            .withProductsList(List.of(
                Product.builder()
                    .withProductId(UUID.randomUUID())
                    .withProductName("Monitor")
                    .withProductCost(new BigDecimal("300.00"))
                    .build()
            ))
            .withOrderAmount(new BigDecimal("300.00"))
            .withOrderStatus(OrderStatus.PENDING)
            .withUserId(EXISTING_USER_ID)
            .build();
        HttpEntity<OrderRequestDto> request = new HttpEntity<>(orderRequestDto);

        ResponseEntity<OrderResponseDto> response = restTemplate.exchange(
            ORDERS_URL,
            HttpMethod.POST,
            request,
            OrderResponseDto.class
        );

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
    }

    @Test
    void methodShouldReturn404WhenGetNonExistentOrderTest() {
        UUID nonExistentOrderId = UUID.randomUUID();
        
        ResponseEntity<String> response = restTemplate.exchange(
            ORDER_BY_ID_URL_TEMPLATE,
            HttpMethod.GET,
            null,
            String.class,
            nonExistentOrderId
        );

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void methodShouldReturn404WhenUpdateStatusOfNonExistentOrderTest() {
        UUID nonExistentOrderId = UUID.randomUUID();
        
        ResponseEntity<String> response = restTemplate.exchange(
            ORDER_STATUS_URL_TEMPLATE + "?orderStatus=DELIVERED",
            HttpMethod.PUT,
            null,
            String.class,
            nonExistentOrderId
        );

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void methodShouldReturn404WhenDeleteNonExistentOrderTest() {
        UUID nonExistentOrderId = UUID.randomUUID();
        
        ResponseEntity<String> response = restTemplate.exchange(
            ORDER_BY_ID_URL_TEMPLATE,
            HttpMethod.DELETE,
            null,
            String.class,
            nonExistentOrderId
        );

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void shouldReturn400WhenGetOrdersByNonExistentUserTest() {
        UUID nonExistentUserId = UUID.randomUUID();
        
        ResponseEntity<String> response = restTemplate.exchange(
            ORDERS_BY_USER_URL_TEMPLATE,
            HttpMethod.GET,
            null,
            String.class,
            nonExistentUserId
        );
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}