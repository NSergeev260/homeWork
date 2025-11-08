package com.example.springDataProjections.usercasses.impl;

import com.example.springDataProjections.persistence.model.DepartmentEntity;
import com.example.springDataProjections.persistence.repository.DepartmentRepository;
import com.example.springDataProjections.usercasses.dto.DepartmentRequestDto;
import com.example.springDataProjections.usercasses.dto.DepartmentResponseDto;
import com.example.springDataProjections.usercasses.mapper.DepartmentMapper;
import com.example.springDataProjections.util.DepartmentTestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    private DepartmentRepository departmentRepo;

    @Mock
    private DepartmentMapper departmentMapper;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Test
    void methodShouldAddDepartmentTest() {
        DepartmentRequestDto requestDto = DepartmentTestData.getDepartmentRequestDto();
        DepartmentEntity departmentEntity = DepartmentTestData.getDepartmentEntity();
        DepartmentResponseDto responseDto = DepartmentTestData.getDepartmentResponseDto();

        Mockito.when(departmentMapper.dtoToEntity(requestDto))
                .thenReturn(departmentEntity);
        Mockito.when(departmentRepo.save(departmentEntity))
                .thenReturn(departmentEntity);
        Mockito.when(departmentMapper.entityToDto(departmentEntity))
                .thenReturn(responseDto);

        DepartmentResponseDto result = departmentService.addDepartment(requestDto);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(DepartmentTestData.DEPARTMENT_NAME,
                result.name());
        Mockito.verify(departmentRepo).save(departmentEntity);
    }

    @Test
    void methodShouldGetDepartmentByIdTest() {
        UUID departmentId = DepartmentTestData.DEPARTMENT_ID;
        DepartmentEntity departmentEntity = DepartmentTestData.getDepartmentEntity();
        DepartmentResponseDto responseDto = DepartmentTestData.getDepartmentResponseDto();

        Mockito.when(departmentRepo.findById(departmentId))
                .thenReturn(Optional.of(departmentEntity));
        Mockito.when(departmentMapper.entityToDto(departmentEntity))
                .thenReturn(responseDto);

        DepartmentResponseDto result = departmentService.getDepartment(departmentId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(departmentId, result.id());
        Assertions.assertEquals(DepartmentTestData.DEPARTMENT_NAME,
                result.name());
        Mockito.verify(departmentRepo).findById(departmentId);
    }

    @Test
    void methodShouldGetAllDepartmentsTest() {
        DepartmentEntity departmentEntity = DepartmentTestData.getDepartmentEntity();
        DepartmentResponseDto responseDto = DepartmentTestData.getDepartmentResponseDto();
        List<DepartmentEntity> departmentList = List.of(departmentEntity);

        Mockito.when(departmentRepo.findAll())
                .thenReturn(departmentList);
        Mockito.when(departmentMapper.entityToDto(departmentEntity))
                .thenReturn(responseDto);

        List<DepartmentResponseDto> result = departmentService.getAllDepartments();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(DepartmentTestData.DEPARTMENT_NAME,
                result.get(0).name());
        Mockito.verify(departmentRepo).findAll();
    }

    @Test
    void methodShouldUpdateDepartmentTest() {
        UUID departmentId = DepartmentTestData.DEPARTMENT_ID;
        DepartmentRequestDto requestDto = DepartmentTestData.getDepartmentRequestDto();
        DepartmentEntity existingDepartment = DepartmentTestData.getDepartmentEntity();
        DepartmentResponseDto responseDto = DepartmentTestData.getDepartmentResponseDto();

        Mockito.when(departmentRepo.findById(departmentId))
                .thenReturn(Optional.of(existingDepartment));
        Mockito.when(departmentRepo.save(existingDepartment))
                .thenReturn(existingDepartment);
        Mockito.when(departmentMapper.entityToDto(existingDepartment))
                .thenReturn(responseDto);

        DepartmentResponseDto result = departmentService.updateDepartment(
                departmentId, requestDto);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(DepartmentTestData.DEPARTMENT_NAME,
                result.name());
        Mockito.verify(departmentRepo).save(existingDepartment);
    }

    @Test
    void methodShouldDeleteDepartmentTest() {
        UUID departmentId = DepartmentTestData.DEPARTMENT_ID;
        DepartmentEntity departmentEntity = DepartmentTestData.getDepartmentEntity();

        Mockito.when(departmentRepo.findById(departmentId))
                .thenReturn(Optional.of(departmentEntity));

        departmentService.deleteDepartment(departmentId);

        Mockito.verify(departmentRepo).delete(departmentEntity);
    }
}