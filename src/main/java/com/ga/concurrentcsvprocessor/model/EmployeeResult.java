package com.ga.concurrentcsvprocessor.model;

import java.math.BigDecimal;

public class EmployeeResult {

    private Employee employee;
    private BigDecimal newSalary;

    public EmployeeResult(Employee employee, BigDecimal newSalary) {
        this.employee = employee;
        this.newSalary = newSalary;
    }

    public Employee getEmployee() {
        return employee;
    }

    public BigDecimal getNewSalary() {
        return newSalary;
    }
}