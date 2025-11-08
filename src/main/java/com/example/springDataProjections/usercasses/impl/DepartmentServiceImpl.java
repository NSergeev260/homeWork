package com.example.springDataProjections.usercasses.impl;

import com.example.springDataProjections.api.exeption.NotFoundException;
import com.example.springDataProjections.persistence.model.DepartmentEntity;
import com.example.springDataProjections.persistence.repository.DepartmentRepository;
import com.example.springDataProjections.usercasses.DepartmentService;
import com.example.springDataProjections.usercasses.dto.DepartmentRequestDto;
import com.example.springDataProjections.usercasses.dto.DepartmentResponseDto;
import com.example.springDataProjections.usercasses.mapper.DepartmentMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepo;
    private final DepartmentMapper departmentMapper;

    @Transactional
    @Override
    public DepartmentResponseDto addDepartment(
            DepartmentRequestDto departmentRequestDto) {
        DepartmentEntity departmentEntity = departmentMapper
                .fromDtoToEntity(departmentRequestDto);
        DepartmentEntity addedDepartmentEntity = departmentRepo
                .save(departmentEntity);

        log.info("NEW Department with id {} has been CREATED, Date: {}",
                addedDepartmentEntity.getId(), LocalDateTime.now());

        return departmentMapper.fromEntityToDto(addedDepartmentEntity);
    }

    @Transactional(readOnly = true)
    @Override
    public DepartmentResponseDto getDepartment(UUID id) {
        DepartmentEntity departmentEntity = getDepartmentRepoByID(id);

        log.info("Department with id {} was FOUND, Date: {}",
                id, LocalDateTime.now());

        return departmentMapper.fromEntityToDto(departmentEntity);
    }

    @Transactional(readOnly = true)
    @Override
    public List<DepartmentResponseDto> getAllDepartments() {

        log.info("Method `getAllDepartments` was run, Date: ",
                LocalDateTime.now());

        return departmentRepo.findAll().stream()
                 .map(departmentMapper::fromEntityToDto)
                 .toList();
    }

    @Transactional
    @Override
    public DepartmentResponseDto updateDepartment(UUID id,
                                                  DepartmentRequestDto departmentRequestDto) {
        DepartmentEntity departmentEntity = getDepartmentRepoByID(id);
        departmentEntity.setName(departmentRequestDto.name());
        DepartmentEntity updatedDepartmentEntity = departmentRepo
                .save(departmentEntity);

        log.info("Department with id {} has been UPDATED, Date: {}",
                updatedDepartmentEntity.getId(), LocalDateTime.now());

        return departmentMapper.fromEntityToDto(updatedDepartmentEntity);
    }

    @Transactional
    @Override
    public void deleteDepartment(UUID id) {
        DepartmentEntity departmentEntity = getDepartmentRepoByID(id);
        departmentRepo.delete(departmentEntity);

        log.info("Department with id {} has been DELETED, Date: {}",
                id, LocalDateTime.now());

    }

    private DepartmentEntity getDepartmentRepoByID(UUID id) {
        DepartmentEntity departmentEntity = departmentRepo.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Department not found. FAIL! ID: " + id));

        return departmentEntity;
    }
}
