package com.ga.concurrentcsvprocessor.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Employee {

    private int employeeId;
    private String name;
    private BigDecimal salary;
    private LocalDate joinedDate;
    private String role;
    private double completedProjectsPercentage;

    public Employee(int employeeId, String name, BigDecimal salary,
                    LocalDate joinedDate, String role,
                    double completedProjectsPercentage) {

        this.employeeId = employeeId;
        this.name = name;
        this.salary = salary;
        this.joinedDate = joinedDate;
        this.role = role;
        this.completedProjectsPercentage = completedProjectsPercentage;
    }
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public LocalDate getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(LocalDate joinedDate) {
        this.joinedDate = joinedDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public double getCompletedProjectsPercentage() {
        return completedProjectsPercentage;
    }

    public void setCompletedProjectsPercentage(double completedProjectsPercentage) {
        this.completedProjectsPercentage = completedProjectsPercentage;
    }


}