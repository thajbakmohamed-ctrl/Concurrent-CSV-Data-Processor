package com.ga.concurrentcsvprocessor;

import com.ga.concurrentcsvprocessor.model.Employee;
import com.ga.concurrentcsvprocessor.reader.CSVReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;
import com.ga.concurrentcsvprocessor.calculator.SalaryCalculator;
import java.math.BigDecimal;

@SpringBootApplication
public class ConcurrentcsvprocessorApplication {

	public static void main(String[] args)throws IOException {
		SpringApplication.run(ConcurrentcsvprocessorApplication.class, args);

		CSVReader reader = new CSVReader();

		List<Employee> employees = reader.readCSV("src/main/resources/test_employees.csv");

		SalaryCalculator calculator = new SalaryCalculator();
		System.out.println("Total employees: " + employees.size());

		for (Employee employee : employees) {
			BigDecimal newSalary = calculator.calculateEmployeeSalary(employee);
			System.out.println(employee.getName()
					+ " | Old Salary: " + employee.getSalary()
					+ " | New Salary: " + newSalary);
		}
	}

}
