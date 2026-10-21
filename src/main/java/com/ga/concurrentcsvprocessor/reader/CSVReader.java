package com.ga.concurrentcsvprocessor.reader;

import com.ga.concurrentcsvprocessor.model.Employee;

import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    public List<Employee> readCSV(String filePath) throws IOException {
        List<Employee> employees = new ArrayList<>();
        Scanner scanner = new Scanner(new File(filePath));
        try {

            EmployeeDataConverter converter = new EmployeeDataConverter();

            int lineNumber = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineNumber++;

                try {
                    Employee employee = converter.convertToEmployee(line);
                    employees.add(employee);

                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid data at line " + lineNumber
                            + ": " + e.getMessage());
                }
            }
        } finally {
            scanner.close();
        }
            return employees;
        }
    }
