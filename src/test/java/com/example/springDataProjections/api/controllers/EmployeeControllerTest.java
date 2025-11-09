package com.example.springDataProjections.api.controllers;

import com.example.springDataProjections.persistence.projection.EmployeeProjection;
import com.example.springDataProjections.persistence.projection.EmployeeSalaryProjection;
import com.example.springDataProjections.usercasses.EmployeeService;
import com.example.springDataProjections.usercasses.dto.EmployeeRequestDto;
import com.example.springDataProjections.usercasses.dto.EmployeeResponseDto;
import com.example.springDataProjections.util.EmployeeTestData;
import com.example.springDataProjections.util.EmployeeProjectionTestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @Test
    void methodShouldAddEmployeeTest() {
        EmployeeRequestDto request = EmployeeTestData.getEmployeeRequestDto();
        EmployeeResponseDto response = EmployeeTestData.getEmployeeResponseDto();

        Mockito.when(employeeService.addEmployee(request))
                .thenReturn(response);

        ResponseEntity<EmployeeResponseDto> result = employeeController.addEmployee(request);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.CREATED,
                result.getStatusCode());
        Assertions.assertEquals(response.id(),
                result.getBody().id());
        Assertions.assertEquals(response.firstName(),
                result.getBody().firstName());
        Assertions.assertEquals(response.lastName(),
                result.getBody().lastName());
        Mockito.verify(employeeService).addEmployee(request);
    }

    @Test
    void methodShouldGetEmployeeByIdTest() {
        EmployeeResponseDto response = EmployeeTestData.getEmployeeResponseDto();
        UUID employeeId = EmployeeTestData.EMPLOYEE_ID;

        Mockito.when(employeeService.getEmployee(employeeId))
                .thenReturn(response);

        ResponseEntity<EmployeeResponseDto> result = employeeController.getEmployee(employeeId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(employeeId, result.getBody().id());
        Assertions.assertEquals(EmployeeTestData.FIRST_NAME,
                result.getBody().firstName());
        Assertions.assertEquals(EmployeeTestData.LAST_NAME,
                result.getBody().lastName());
        Mockito.verify(employeeService).getEmployee(employeeId);
    }

    @Test
    void methodShouldGetAllEmployeesTest() {
        List<EmployeeResponseDto> response = List.of(EmployeeTestData
                .getEmployeeResponseDto());

        Mockito.when(employeeService.getAllEmployees())
                .thenReturn(response);

        ResponseEntity<List<EmployeeResponseDto>> result = employeeController
                .getAllEmployees();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(1, result.getBody().size());
        Assertions.assertEquals(EmployeeTestData.FIRST_NAME,
                result.getBody().get(0).firstName());
        Assertions.assertEquals(EmployeeTestData.LAST_NAME,
                result.getBody().get(0).lastName());
        Mockito.verify(employeeService).getAllEmployees();
    }

    @Test
    void methodShouldUpdateEmployeeTest() {
        EmployeeRequestDto request = EmployeeTestData.getEmployeeRequestDto();
        EmployeeResponseDto response = EmployeeTestData.getEmployeeResponseDto();
        UUID employeeId = EmployeeTestData.EMPLOYEE_ID;

        Mockito.when(employeeService.updateEmployee(employeeId, request))
                .thenReturn(response);

        ResponseEntity<EmployeeResponseDto> result = employeeController
                .updateEmployee(employeeId, request);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(response.id(), result.getBody().id());
        Assertions.assertEquals(response.firstName(), result.getBody().firstName());
        Mockito.verify(employeeService).updateEmployee(employeeId, request);
    }

    @Test
    void methodShouldDeleteEmployeeTest() {
        UUID employeeId = EmployeeTestData.EMPLOYEE_ID;

        ResponseEntity<Void> result = employeeController.deleteEmployee(employeeId);

        Assertions.assertEquals(HttpStatus.NO_CONTENT,
                result.getStatusCode());
        Mockito.verify(employeeService).deleteEmployee(employeeId);
    }

    @Test
    void methodShouldGetEmployeeProjectionByIdTest() {
        EmployeeProjection projection = EmployeeProjectionTestData.getEmployeeProjection();
        UUID employeeId = EmployeeTestData.EMPLOYEE_ID;

        Mockito.when(employeeService.getEmployeeProjectionById(employeeId))
                .thenReturn(Optional.of(projection));

        Optional<EmployeeProjection> result = employeeController
                .getEmployeeProjectionById(employeeId);

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("Doe John", result.get().getFullName());
        Assertions.assertEquals("CTO", result.get().getPosition());
        Mockito.verify(employeeService).getEmployeeProjectionById(employeeId);
    }

    @Test
    void methodShouldGetEmployeeScroogeMcDuckLevelProjectionsTest() {
        List<EmployeeSalaryProjection> projections = List.of(
                EmployeeProjectionTestData.getEmployeeSalaryProjection());

        Mockito.when(employeeService.getEmployeeScroogeMcDuckLevelProjections())
                .thenReturn(projections);

        List<EmployeeSalaryProjection> result = employeeController.getEmployeeScroogeMcDuckLevelProjections();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Doe John", result.get(0).getFullName());
        Assertions.assertEquals("IT", result.get(0).getDepartmentName());
        Mockito.verify(employeeService).getEmployeeScroogeMcDuckLevelProjections();
    }
}