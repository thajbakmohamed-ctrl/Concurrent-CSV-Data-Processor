package com.ga.concurrentcsvprocessor.reader;

import com.ga.concurrentcsvprocessor.model.Employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import com.ga.concurrentcsvprocessor.validation.EmployeeValidator;

public class EmployeeDataConverter {
    //take employee data from CSV and create an employee object
    public Employee convertToEmployee(String line) {
        String[] data = line.split(",", -1);
        if (data.length != 6) {
            throw new IllegalArgumentException("Employee data must contain 6 fields");
        }
        for (String value : data) {
            if (value.trim().isEmpty()) {
                throw new IllegalArgumentException("Employee data cannot be empty");
            }
        }

        int employeeId = Integer.parseInt(data[0]);
        BigDecimal salary = new BigDecimal(data[2]);
        LocalDate joinedDate = LocalDate.parse(data[3]);
        double completedProjectsPercentage = Double.parseDouble(data[5]);
        EmployeeValidator validator = new EmployeeValidator();
        validator.validateSalary(salary);
        validator.validateCompletedProjects(completedProjectsPercentage);
        validator.validateRole(data[4]);
        validator.validateJoinedDate(joinedDate);

        return new Employee(employeeId, data[1], salary, joinedDate,
                data[4], completedProjectsPercentage);
    }
}
