package com.ga.concurrentcsvprocessor;

import com.ga.concurrentcsvprocessor.model.Employee;
import com.ga.concurrentcsvprocessor.reader.CSVReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class ConcurrentcsvprocessorApplication {

	public static void main(String[] args)throws IOException {
		SpringApplication.run(ConcurrentcsvprocessorApplication.class, args);

		CSVReader reader = new CSVReader();

		List<Employee> employees = reader.readCSV(
				"src/main/resources/employees.csv"
		);
		System.out.println("Total employees: " + employees.size());

		for (Employee employee : employees) {
			System.out.println(employee.getName() + " - " + employee.getSalary());
		}
	}

}
