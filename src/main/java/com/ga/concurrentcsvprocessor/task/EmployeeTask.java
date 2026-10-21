package com.ga.concurrentcsvprocessor.task;

import com.ga.concurrentcsvprocessor.model.Employee;
import com.ga.concurrentcsvprocessor.calculator.SalaryCalculator;

import java.math.BigDecimal;
import com.ga.concurrentcsvprocessor.model.EmployeeResult;
import com.ga.concurrentcsvprocessor.collector.ResultCollector;
import java.util.concurrent.Semaphore;

public class EmployeeTask implements Runnable {

    private Employee employee;
    private SalaryCalculator calculator;
    private ResultCollector collector;
    private Semaphore semaphore;

    public EmployeeTask(Employee employee, SalaryCalculator calculator,
                        ResultCollector collector, Semaphore semaphore) {

        this.employee = employee;
        this.calculator = calculator;
        this.collector = collector;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        boolean acquired = false;

        try {
            semaphore.acquire();
            acquired = true;
            BigDecimal newSalary = calculator.calculateEmployeeSalary(employee);
            EmployeeResult result = new EmployeeResult(employee, newSalary);
            collector.addResult(result);
            String threadName = Thread.currentThread().getName();
            System.out.println("Thread: " + threadName
                    + " | Employee: " + employee.getName()
                    + " | Old Salary: " + employee.getSalary()
                    + " | New Salary: " + newSalary);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Employee processing interrupted: "
                    + employee.getName());

        } finally {
            if (acquired) {
                semaphore.release();
            }
        }
    }
}
