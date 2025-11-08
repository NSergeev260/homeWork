package com.example.springDataProjections.usercasses.impl;

import com.example.springDataProjections.api.exeption.NotFoundException;
import com.example.springDataProjections.persistence.model.DepartmentEntity;
import com.example.springDataProjections.persistence.model.EmployeeEntity;
import com.example.springDataProjections.persistence.projection.EmployeeProjection;
import com.example.springDataProjections.persistence.projection.EmployeeSalaryProjection;
import com.example.springDataProjections.persistence.repository.DepartmentRepository;
import com.example.springDataProjections.persistence.repository.EmployeeRepository;
import com.example.springDataProjections.usercasses.EmployeeService;
import com.example.springDataProjections.usercasses.dto.EmployeeRequestDto;
import com.example.springDataProjections.usercasses.dto.EmployeeResponseDto;
import com.example.springDataProjections.usercasses.mapper.EmployeeMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepo;
    private final DepartmentRepository departmentRepo;

    @Transactional
    @Override
    public EmployeeResponseDto addEmployee(EmployeeRequestDto employeeRequestDto) {
        EmployeeEntity employeeEntity = employeeMapper.dtoToEntity(employeeRequestDto);
        EmployeeEntity addedEmployeeEntity = employeeRepo.save(employeeEntity);

        log.info("NEW Employee with id {} has been CREATED, Date: {}",
                addedEmployeeEntity.getId(), LocalDateTime.now());

        return employeeMapper.entityToDto(addedEmployeeEntity);
    }

    @Transactional(readOnly = true)
    @Override
    public EmployeeResponseDto getEmployee(UUID id) {
        EmployeeEntity employeeEntity = getEmployeeRepoById(id);

        log.info("Employee with id {} was FOUND, Date: {}",
                id, LocalDateTime.now());

        return employeeMapper.entityToDto(employeeEntity);
    }

    @Transactional(readOnly = true)
    @Override
    public List<EmployeeResponseDto> getAllEmployees() {

        log.info("Method `getAllEmployees` was run, Date: {}",
                LocalDateTime.now());

        return employeeRepo.findAll().stream()
                .map(employeeMapper::entityToDto)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<EmployeeProjection> getEmployeeProjectionById(UUID id) {

        log.info("Method `getEmployeeProjectionById` was run, Date: {}", LocalDateTime.now());

        return employeeRepo.findEmployeeProjectionById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<EmployeeSalaryProjection> getEmployeeSalaryProjections() {

        log.info("Method `getEmployeeSalaryProjections` was run, Date: {}", LocalDateTime.now());

        return employeeRepo.findEmployeeSalaryProjections();
    }

    @Transactional
    @Override
    public EmployeeResponseDto updateEmployee(UUID id, EmployeeRequestDto employeeRequestDto) {
        EmployeeEntity employeeEntity = getEmployeeRepoById(id);
        employeeEntity.setFirstName(employeeRequestDto.firstName());
        employeeEntity.setLastName(employeeRequestDto.lastName());
        employeeEntity.setPosition(employeeRequestDto.position());
        employeeEntity.setSalary(employeeRequestDto.salary());

        DepartmentEntity department = departmentRepo.findByName(employeeRequestDto.departmentName())
                .orElseThrow(() -> new NotFoundException("Department not found. FAIL!"));

        employeeEntity.setDepartment(department);

        EmployeeEntity updatedEmployeeEntity = employeeRepo.save(employeeEntity);

        log.info("Employee with id {} has been UPDATED, Date: {}",
                updatedEmployeeEntity.getId(), LocalDateTime.now());

        return employeeMapper.entityToDto(updatedEmployeeEntity);
    }

    @Transactional
    @Override
    public void deleteEmployee(UUID id) {
        EmployeeEntity employeeEntity = getEmployeeRepoById(id);
        employeeRepo.delete(employeeEntity);

        log.info("Employee with id {} has been DELETED, Date: {}",
                id, LocalDateTime.now());

    }

    private EmployeeEntity getEmployeeRepoById(UUID id) {
        EmployeeEntity employeeEntity = employeeRepo.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Employee not found. FAIL! ID: " + id));

        return employeeEntity;
    }
}
