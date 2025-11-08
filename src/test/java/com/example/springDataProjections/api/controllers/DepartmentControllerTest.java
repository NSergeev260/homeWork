package com.example.springDataProjections.api.controllers;

import com.example.springDataProjections.usercasses.DepartmentService;
import com.example.springDataProjections.usercasses.dto.DepartmentRequestDto;
import com.example.springDataProjections.usercasses.dto.DepartmentResponseDto;
import com.example.springDataProjections.util.DepartmentTestData;
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
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class DepartmentControllerTest {

    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private DepartmentController departmentController;

    @Test
    void methodShouldAddDepartmentTest() {
        DepartmentRequestDto request = DepartmentTestData.getDepartmentRequestDto();
        DepartmentResponseDto response = DepartmentTestData.getDepartmentResponseDto();

        Mockito.when(departmentService.addDepartment(request))
                .thenReturn(response);

        ResponseEntity<DepartmentResponseDto> result = departmentController
                .addDepartment(request);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.CREATED,
                result.getStatusCode());
        Assertions.assertEquals(response.id(),
                result.getBody().id());
        Assertions.assertEquals(response.name(),
                result.getBody().name());
        Mockito.verify(departmentService).addDepartment(request);
    }

    @Test
    void methodShouldGetDepartmentByIdTest() {
        DepartmentResponseDto response = DepartmentTestData.getDepartmentResponseDto();
        UUID departmentId = DepartmentTestData.DEPARTMENT_ID;

        Mockito.when(departmentService.getDepartment(departmentId))
                .thenReturn(response);

        ResponseEntity<DepartmentResponseDto> result = departmentController
                .getDepartment(departmentId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.OK,
                result.getStatusCode());
        Assertions.assertEquals(departmentId,
                result.getBody().id());
        Assertions.assertEquals(DepartmentTestData.DEPARTMENT_NAME,
                result.getBody().name());
        Mockito.verify(departmentService).getDepartment(departmentId);
    }

    @Test
    void methodShouldGetAllDepartmentsTest() {
        List<DepartmentResponseDto> response = List.of(DepartmentTestData
                .getDepartmentResponseDto());

        Mockito.when(departmentService.getAllDepartments())
                .thenReturn(response);

        ResponseEntity<List<DepartmentResponseDto>> result = departmentController
                .getAllDepartments();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.OK,
                result.getStatusCode());
        Assertions.assertEquals(1, result.getBody().size());
        Assertions.assertEquals(DepartmentTestData.DEPARTMENT_NAME,
                result.getBody().get(0).name());
        Mockito.verify(departmentService).getAllDepartments();
    }

    @Test
    void methodShouldUpdateDepartmentTest() {
        DepartmentRequestDto request = DepartmentTestData.getDepartmentRequestDto();
        DepartmentResponseDto response = DepartmentTestData.getDepartmentResponseDto();
        UUID departmentId = DepartmentTestData.DEPARTMENT_ID;

        Mockito.when(departmentService.updateDepartment(
                departmentId, request))
                .thenReturn(response);

        ResponseEntity<DepartmentResponseDto> result = departmentController
                .updateDepartment(departmentId, request);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.OK,
                result.getStatusCode());
        Assertions.assertEquals(response.id(),
                result.getBody().id());
        Assertions.assertEquals(response.name(),
                result.getBody().name());
        Mockito.verify(departmentService).updateDepartment(
                departmentId, request);
    }

    @Test
    void methodShouldDeleteDepartmentTest() {
        UUID departmentId = DepartmentTestData.DEPARTMENT_ID;

        ResponseEntity<Void> result = departmentController.deleteDepartment(departmentId);

        Assertions.assertEquals(HttpStatus.NO_CONTENT,
                result.getStatusCode());
        Mockito.verify(departmentService).deleteDepartment(departmentId);
    }
}