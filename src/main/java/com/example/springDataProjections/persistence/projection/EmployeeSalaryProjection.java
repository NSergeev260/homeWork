package com.example.springDataProjections.persistence.projection;

import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

public interface EmployeeSalaryProjection {
    String getFullName();

    String getDepartmentName();

    BigDecimal getSalary();

    @Value("#{target.salary > 400000 ? 'High' : " +
            "#{target.salary > 220000 ? 'Medium' : 'Low'}}")
    String getSalaryCategory();
}