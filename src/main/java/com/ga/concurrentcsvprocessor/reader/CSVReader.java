package com.ga.concurrentcsvprocessor.reader;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;
import java.io.IOException;

public class CSVReader {

    public void readCSV(String filePath) throws IOException {
        Scanner scanner = new Scanner(new File(filePath));

        //skip the header
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }

        //read employee records
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] data = line.split(",");
            System.out.println(data[0]);
            BigDecimal salary = new BigDecimal(data[1]);
            LocalDate joinedDate = LocalDate.parse(data[2]);
            double completedProjectsPercentage = Double.parseDouble(data[4]);
        }

        scanner.close();
    }
}