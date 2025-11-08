package com.example.springDataProjections.usercasses.impl;

import com.example.springDataProjections.persistence.model.DepartmentEntity;
import com.example.springDataProjections.persistence.model.EmployeeEntity;
import com.example.springDataProjections.persistence.repository.DepartmentRepository;
import com.example.springDataProjections.persistence.repository.EmployeeRepository;
import com.example.springDataProjections.usercasses.dto.EmployeeRequestDto;
import com.example.springDataProjections.usercasses.dto.EmployeeResponseDto;
import com.example.springDataProjections.usercasses.mapper.EmployeeMapper;
import com.example.springDataProjections.util.EmployeeTestData;
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
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepo;

    @Mock
    private DepartmentRepository departmentRepo;

    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void methodShouldAddEmployeeTest() {
        EmployeeRequestDto requestDto = EmployeeTestData.getEmployeeRequestDto();
        EmployeeEntity employeeEntity = EmployeeTestData.getEmployeeEntity();
        EmployeeResponseDto responseDto = EmployeeTestData.getEmployeeResponseDto();
        DepartmentEntity department = EmployeeTestData.getEmployeeEntity().getDepartment();

        Mockito.when(employeeMapper.dtoToEntity(requestDto))
                .thenReturn(employeeEntity);
        Mockito.when(employeeRepo.save(employeeEntity))
                .thenReturn(employeeEntity);
        Mockito.when(employeeMapper.entityToDto(employeeEntity))
                .thenReturn(responseDto);

        EmployeeResponseDto result = employeeService.addEmployee(requestDto);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(EmployeeTestData.FIRST_NAME,
                result.firstName());
        Assertions.assertEquals(EmployeeTestData.LAST_NAME,
                result.lastName());
        Assertions.assertEquals(EmployeeTestData.SALARY,
                result.salary());
        Mockito.verify(employeeRepo).save(employeeEntity);
    }

    @Test
    void methodShouldGetEmployeeByIdTest() {
        UUID employeeId = EmployeeTestData.EMPLOYEE_ID;
        EmployeeEntity employeeEntity = EmployeeTestData.getEmployeeEntity();
        EmployeeResponseDto responseDto = EmployeeTestData.getEmployeeResponseDto();

        Mockito.when(employeeRepo.findById(employeeId))
                .thenReturn(Optional.of(employeeEntity));
        Mockito.when(employeeMapper.entityToDto(employeeEntity))
                .thenReturn(responseDto);

        EmployeeResponseDto result = employeeService.getEmployee(employeeId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(employeeId, result.id());
        Assertions.assertEquals(EmployeeTestData.FIRST_NAME,
                result.firstName());
        Mockito.verify(employeeRepo).findById(employeeId);
    }

    @Test
    void methodShouldGetAllEmployeesTest() {
        EmployeeEntity employeeEntity = EmployeeTestData.getEmployeeEntity();
        EmployeeResponseDto responseDto = EmployeeTestData.getEmployeeResponseDto();
        List<EmployeeEntity> employeeList = List.of(employeeEntity);

        Mockito.when(employeeRepo.findAll())
                .thenReturn(employeeList);
        Mockito.when(employeeMapper.entityToDto(employeeEntity))
                .thenReturn(responseDto);

        List<EmployeeResponseDto> result = employeeService.getAllEmployees();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(EmployeeTestData.FIRST_NAME,
                result.get(0).firstName());
        Mockito.verify(employeeRepo).findAll();
    }

    @Test
    void methodShouldUpdateEmployeeTest() {
        UUID employeeId = EmployeeTestData.EMPLOYEE_ID;
        EmployeeRequestDto requestDto = EmployeeTestData.getEmployeeRequestDto();
        EmployeeEntity existingEmployee = EmployeeTestData.getEmployeeEntity();
        EmployeeResponseDto responseDto = EmployeeTestData.getEmployeeResponseDto();
        DepartmentEntity department = EmployeeTestData.getEmployeeEntity().getDepartment();

        Mockito.when(employeeRepo.findById(employeeId))
                .thenReturn(Optional.of(existingEmployee));
        Mockito.when(departmentRepo.findByName(
                EmployeeTestData.DEPARTMENT_NAME))
                .thenReturn(Optional.of(department));
        Mockito.when(employeeRepo.save(existingEmployee))
                .thenReturn(existingEmployee);
        Mockito.when(employeeMapper.entityToDto(existingEmployee))
                .thenReturn(responseDto);

        EmployeeResponseDto result = employeeService.updateEmployee(
                employeeId, requestDto);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(EmployeeTestData.FIRST_NAME,
                result.firstName());
        Mockito.verify(employeeRepo).save(existingEmployee);
    }

    @Test
    void methodShouldDeleteEmployeeTest() {
        UUID employeeId = EmployeeTestData.EMPLOYEE_ID;
        EmployeeEntity employeeEntity = EmployeeTestData.getEmployeeEntity();

        Mockito.when(employeeRepo.findById(employeeId))
                .thenReturn(Optional.of(employeeEntity));

        employeeService.deleteEmployee(employeeId);

        Mockito.verify(employeeRepo).delete(employeeEntity);
    }
}