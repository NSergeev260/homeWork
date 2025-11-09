package com.example.springDataProjections.api.controllers;

import com.example.springDataProjections.api.exeption.NotFoundException;
import com.example.springDataProjections.persistence.projection.EmployeeProjection;
import com.example.springDataProjections.persistence.projection.EmployeeSalaryProjection;
import com.example.springDataProjections.usercasses.EmployeeService;
import com.example.springDataProjections.usercasses.dto.EmployeeRequestDto;
import com.example.springDataProjections.usercasses.dto.EmployeeResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeResponseDto> addEmployee(
            @Valid @RequestBody EmployeeRequestDto employeeRequestDto) {
        EmployeeResponseDto employeeResponseDto = employeeService.addEmployee(employeeRequestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(employeeResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> getEmployee(@PathVariable UUID id) {
        EmployeeResponseDto employeeResponseDto = employeeService.getEmployee(id);

        return ResponseEntity
                .ok()
                .body(employeeResponseDto);
    }


    @GetMapping("/search")
    public ResponseEntity<List<EmployeeResponseDto>> getAllEmployees() {
        List<EmployeeResponseDto> employeeList = employeeService.getAllEmployees();

        return ResponseEntity
                .ok(employeeList);
    }


    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> updateEmployee(
            @PathVariable UUID id, @Valid @RequestBody EmployeeRequestDto employeeRequestDto) {
        EmployeeResponseDto employeeResponseDto = employeeService.updateEmployee(id, employeeRequestDto);

        return ResponseEntity
                .ok()
                .body(employeeResponseDto);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteEmployee(@PathVariable UUID id) {
        employeeService.deleteEmployee(id);

        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping("/{id}/projection")
    public Optional<EmployeeProjection> getEmployeeProjectionById(@PathVariable UUID id) {

        return Optional.ofNullable(employeeService.getEmployeeProjectionById(id)
                .orElseThrow(() -> new NotFoundException("Employee projection not found")));
    }

    @GetMapping("/ScroogeMcDuckLevel-projections")
    public List<EmployeeSalaryProjection> getEmployeeScroogeMcDuckLevelProjections() {
        return employeeService.getEmployeeScroogeMcDuckLevelProjections();
    }
}
