package com.example.springDataProjections.util;

import com.example.springDataProjections.persistence.projection.EmployeeProjection;
import com.example.springDataProjections.persistence.projection.EmployeeSalaryProjection;

import java.math.BigDecimal;

public class EmployeeProjectionTestData {

    public static EmployeeProjection getEmployeeProjection() {

        return new EmployeeProjection() {
            @Override
            public String getFullName() {
                return "Doe John";
            }

            @Override
            public String getPosition() {
                return "CTO";
            }

            @Override
            public String getDepartmentName() {
                return "IT";
            }
        };
    }

    public static EmployeeSalaryProjection getEmployeeSalaryProjection() {

        return new EmployeeSalaryProjection() {
            @Override
            public String getFullName() {
                return "Doe John";
            }

            @Override
            public String getDepartmentName() {
                return "IT";
            }

            @Override
            public BigDecimal getSalary() {
                return new BigDecimal("450000.00");
            }

            @Override
            public String getScroogeMcDuckLevel() {
                return "High";
            }
        };
    }
}