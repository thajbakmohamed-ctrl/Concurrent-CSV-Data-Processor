package com.ga.concurrentcsvprocessor.calculator;
import com.ga.concurrentcsvprocessor.model.Employee;

import java.time.LocalDate;
import java.time.Period;
import java.math.BigDecimal;
public class SalaryCalculator {

    public double calculateRoleIncrease(String role) {

        if (role.equalsIgnoreCase("Director")) {
            return 5;

        } else if (role.equalsIgnoreCase("Manager")) {
            return 2;

        } else {
            return 1;
        }
    }

    public double calculateYearsIncrease(LocalDate joinedDate) {
        LocalDate today = LocalDate.now();
        int years = Period.between(joinedDate, today).getYears();
        return years * 2;
    }

    public double calculateFinalIncrease(double completedProjectsPercentage,
                                         double roleIncrease, double yearsIncrease) {

        if (completedProjectsPercentage < 0.6) {
            return 0;
        }
        double totalIncrease = roleIncrease + yearsIncrease;
        if (completedProjectsPercentage > 0.8) {
            totalIncrease = totalIncrease * 1.5;
        }

        return totalIncrease;
    }

    public BigDecimal calculateNewSalary(BigDecimal salary, double increasePercentage) {
        BigDecimal percentage = BigDecimal.valueOf(increasePercentage);
        BigDecimal increaseAmount = salary.multiply(percentage).divide(BigDecimal.valueOf(100));
        BigDecimal newSalary = salary.add(increaseAmount);

        return newSalary;
    }

    public BigDecimal calculateEmployeeSalary(Employee employee) {
        double roleIncrease = calculateRoleIncrease(employee.getRole());
        double yearsIncrease = calculateYearsIncrease(employee.getJoinedDate());
        double finalIncrease = calculateFinalIncrease(
                employee.getCompletedProjectsPercentage(), roleIncrease, yearsIncrease);
        return calculateNewSalary(employee.getSalary(), finalIncrease);
    }
}
