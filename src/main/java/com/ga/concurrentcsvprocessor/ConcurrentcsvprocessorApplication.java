package com.ga.concurrentcsvprocessor;

import com.ga.concurrentcsvprocessor.model.Employee;
import com.ga.concurrentcsvprocessor.reader.CSVReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;
import com.ga.concurrentcsvprocessor.calculator.SalaryCalculator;
import com.ga.concurrentcsvprocessor.task.EmployeeTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import com.ga.concurrentcsvprocessor.collector.ResultCollector;
import com.ga.concurrentcsvprocessor.model.EmployeeResult;
import java.util.concurrent.Semaphore;

@SpringBootApplication
public class ConcurrentcsvprocessorApplication {

	public static void main(String[] args)throws IOException {
		SpringApplication.run(ConcurrentcsvprocessorApplication.class, args);

		CSVReader reader = new CSVReader();

		List<Employee> employees = reader.readCSV("src/main/resources/test_employees.csv");

		SalaryCalculator calculator = new SalaryCalculator();

		ResultCollector collector = new ResultCollector();

		Semaphore semaphore = new Semaphore(2);
		ExecutorService executor = Executors.newFixedThreadPool(4);
		System.out.println("Total employees: " + employees.size());

		for (Employee employee : employees) {
			EmployeeTask task = new EmployeeTask(employee, calculator, collector, semaphore);
			executor.submit(task);
		}
		//stop accepting new tasks
		executor.shutdown();
		//wait for all employee tasks to finish
		try {
			boolean finished = executor.awaitTermination(1, TimeUnit.MINUTES);

			if (finished) {
				List<EmployeeResult> results = collector.getResults();

				System.out.println("Processed employees: " + results.size());

				System.out.println("Atomic Counter: " + collector.getProcessedCount());

				if (results.size() == employees.size()) {
					System.out.println("All employee salaries calculated successfully");
				} else {
					System.out.println("Some employee results are missing");
				}

			} else {
				System.out.println("Some employee tasks are still running");
			}

		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			System.out.println("Thread processing was interrupted");
		}
	}

}
