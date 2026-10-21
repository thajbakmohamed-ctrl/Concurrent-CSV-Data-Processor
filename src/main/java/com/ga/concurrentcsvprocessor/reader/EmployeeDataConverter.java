package com.ga.concurrentcsvprocessor.reader;

import com.ga.concurrentcsvprocessor.model.Employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import com.ga.concurrentcsvprocessor.validation.EmployeeValidator;

public class EmployeeDataConverter {
    //take employee data from CSV and create an employee object
    public Employee convertToEmployee(String line) {
        String[] data = line.split(",", -1);
        if (data.length != 5) {
            throw new IllegalArgumentException("Employee data must contain 5 fields");
        }
        for (String value : data) {
            if (value.trim().isEmpty()) {
                throw new IllegalArgumentException("Employee data cannot be empty");
            }
        }
        BigDecimal salary = new BigDecimal(data[1]);
        LocalDate joinedDate = LocalDate.parse(data[2]);
        double completedProjectsPercentage = Double.parseDouble(data[4]);
        EmployeeValidator validator = new EmployeeValidator();
        validator.validateSalary(salary);
        validator.validateCompletedProjects(completedProjectsPercentage);
        validator.validateRole(data[3]);
        validator.validateJoinedDate(joinedDate);

        return new Employee(data[0], salary, joinedDate, data[3],
                completedProjectsPercentage);
    }
}
