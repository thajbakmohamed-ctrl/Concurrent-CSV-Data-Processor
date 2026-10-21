package com.ga.concurrentcsvprocessor.validation;
import java.math.BigDecimal;
import java.time.LocalDate;

public class EmployeeValidator {
    public void validateSalary(BigDecimal salary) {

        if (salary.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Salary must be greater than zero");
        }
    }
    public void validateCompletedProjects(double percentage) {
        //check if percentage is not a number or outside 0 to 100
        if (percentage != percentage || percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("Completed projects percentage must be between 0 and 100");
        }
    }
    public void validateRole(String role) {

        if (!role.equalsIgnoreCase("Director") &&
                !role.equalsIgnoreCase("Manager") &&
                !role.equalsIgnoreCase("Employee")) {

            throw new IllegalArgumentException("Invalid employee role");
        }
    }

    public void validateJoinedDate(LocalDate joinedDate) {

        if (joinedDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Joined date cannot be in the future");
        }
    }
}
