package com.example.springDataProjections.api.controllers;

import com.example.springDataProjections.usercasses.DepartmentService;
import com.example.springDataProjections.usercasses.dto.DepartmentRequestDto;
import com.example.springDataProjections.usercasses.dto.DepartmentResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentResponseDto> addDepartment(
            @Valid @RequestBody DepartmentRequestDto departmentRequestDto) {
        DepartmentResponseDto departmentResponseDto = departmentService.addDepartment(departmentRequestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(departmentResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponseDto> getDepartment(@PathVariable UUID id) {
        DepartmentResponseDto departmentResponseDto = departmentService.getDepartment(id);

        return ResponseEntity
                .ok()
                .body(departmentResponseDto);
    }


    @GetMapping("/search")
    public ResponseEntity<List<DepartmentResponseDto>> getAllDepartments() {
        List<DepartmentResponseDto> departmentsList = departmentService.getAllDepartments();

        return ResponseEntity
                .ok(departmentsList);
    }


    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponseDto> updateDepartment(
            @PathVariable UUID id, @Valid @RequestBody DepartmentRequestDto departmentRequestDto) {
        DepartmentResponseDto departmentResponseDto = departmentService.updateDepartment(id, departmentRequestDto);

        return ResponseEntity
                .ok()
                .body(departmentResponseDto);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteDepartment(@PathVariable UUID id) {
        departmentService.deleteDepartment(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}
