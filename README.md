# Concurrent CSV Data Processor

## Project Overview

Concurrent CSV Data Processor is a Java Spring Boot application that reads employee records from a CSV file and calculates salary increases based on predefined business rules.

The application uses multithreading to process employee records concurrently and concurrency control mechanisms to manage shared data safely.

## Technologies Used

- Java
- Spring Boot
- Maven
- Java Executor Framework
- Runnable
- ReentrantLock
- Semaphore
- AtomicInteger
- Git and GitHub

## Main Features

- Reads employee information from a CSV file.
- Validates employee data before processing.
- Calculates salary increases based on employee role and completed years of service.
- Prevents salary increases for employees who completed less than 60% of their projects.
- Applies a 1.5 multiplier to salary increases when project completion exceeds 80%.
- Uses a fixed thread pool to process employees concurrently.
- Uses ReentrantLock to protect shared employee results.
- Uses Semaphore to limit concurrent salary calculations.
- Uses AtomicInteger to count successfully processed employee records.
- Displays original and updated employee salaries.


## Project Setup Instructions

### Requirements

- Java JDK
- Maven
- IntelliJ IDEA or another Java IDE

### How to Run the Application

1. Clone the GitHub repository.
2. Open the project in IntelliJ IDEA.
3. Make sure Java and Maven are configured.
4. Locate the sample CSV file:
   `src/main/resources/test_employees.csv`
5. Run `ConcurrentcsvprocessorApplication.java`.
6. View the employee salary calculations in the Console.

### CSV File Format

The application reads employee records with the following fields:

- Employee ID
- Name
- Salary
- Joined Date
- Role
- Completed Projects Percentage

Example:

1,Alice,52000.0,2019-05-12,Employee,0.8

The completed projects percentage is stored as a value between 0 and 1.

### Expected Output

The application processes 30 employees using a fixed thread pool.

After successful processing, the Console displays:

Total employees: 30
Processed employees: 30
Atomic Counter: 30
All employee salaries calculated successfully


## Project Architecture

The application is organized into separate classes, with each class responsible for a specific part of employee data processing.

### Classes and Responsibilities

| Class | Responsibility |
|---|---|
| Employee | Stores employee information, including ID, name, salary, joined date, role, and project completion percentage. |
| CSVReader | Reads employee records from the CSV file and handles invalid rows. |
| EmployeeDataConverter | Converts each CSV row into an Employee object. |
| EmployeeValidator | Validates employee salary, role, joined date, and project completion percentage. |
| SalaryCalculator | Calculates employee salary increases based on the business rules. |
| EmployeeTask | Implements Runnable to process an employee's salary in a thread. |
| EmployeeResult | Stores the employee and calculated new salary. |
| ResultCollector | Safely stores calculated employee results using ReentrantLock and AtomicInteger. |
| ConcurrentcsvprocessorApplication | Starts the application, creates the thread pool, submits employee tasks, and checks the processing results. |
| SalaryCalculatorTest | Runs simple tests to verify salary calculation rules. |

### Processing Workflow

1. The application reads employee data from `test_employees.csv`.
2. CSVReader converts valid CSV records into Employee objects.
3. EmployeeValidator checks the employee data.
4. ExecutorService submits EmployeeTask objects to a fixed pool of four threads.
5. Semaphore limits salary calculations to two tasks at a time.
6. SalaryCalculator calculates the updated salary.
7. ResultCollector uses ReentrantLock to safely store each result.
8. AtomicInteger counts the successfully saved results.
9. The application checks that all employee records have been processed.

### Main Methods

| Method | Description |
|---|---|
| `readCSV()` | Reads employee records from the CSV file. |
| `convertToEmployee()` | Converts a CSV row into an Employee object. |
| `validateSalary()` | Checks that the salary is positive. |
| `validateCompletedProjects()` | Checks that project completion is between 0 and 1. |
| `validateRole()` | Checks that the employee role is valid. |
| `validateJoinedDate()` | Rejects future joining dates. |
| `calculateRoleIncrease()` | Returns the salary increase percentage for an employee's role. |
| `calculateYearsIncrease()` | Calculates a 2% increase for every completed year of service. |
| `calculateFinalIncrease()` | Applies project completion rules and the bonus. |
| `calculateNewSalary()` | Calculates the updated salary. |
| `calculateEmployeeSalary()` | Combines salary calculation methods for one employee. |
| `run()` | Executes an employee salary processing task. |
| `addResult()` | Adds an employee result to the shared list using a lock. |
| `getResults()` | Returns a copy of the saved employee results. |
| `getProcessedCount()` | Returns the number of successfully saved results. |


## Salary Increase Rules

The application calculates salary increases according to the following rules:

### Role-Based Increase

- Director: 5%
- Manager: 2%
- Employee: 1%

### Years of Service

Employees receive an additional 2% salary increase for each completed year of service.

Only completed years are counted.

### Project Completion

- Below 60%: No salary increase.
- From 60% to 80%: Normal salary increase.
- Above 80%: The total salary increase is multiplied by 1.5 (bonus feature).

### Salary Calculation

The total increase is calculated by adding the role-based increase and the years-of-service increase.

The final salary is calculated as:

New Salary = Original Salary + (Original Salary × Final Increase Percentage / 100)

## Concurrency Implementation

### ExecutorService

The application uses a fixed thread pool containing four worker threads to process employee salary calculations concurrently.

### Runnable

Each EmployeeTask implements Runnable and calculates the salary of one employee.

### ReentrantLock

ResultCollector uses ReentrantLock to protect the shared results list from concurrent modifications.

### Semaphore

A Semaphore with two permits limits the number of tasks that can enter the salary calculation section simultaneously.

### AtomicInteger

AtomicInteger counts the number of employee results successfully stored during concurrent processing.

### Thread Completion

The application uses shutdown() and awaitTermination() to stop accepting new tasks and wait for submitted tasks to finish.

## Testing and Results

The application was tested using the provided CSV file containing 30 employee records.

The execution produced:

- Total employees read: 30
- Employee results saved: 30
- Atomic counter: 30
- Four worker threads used for processing

Seven salary calculation checks were also performed, covering project completion thresholds and completed years of service. All seven produced the expected results.

## Future Improvements

Possible future improvements include adding a frontend interface, supporting CSV file uploads, and allowing users to adjust salary increase percentages interactively.



## Project Documentation

For a detailed explanation of the project implementation,
concurrency mechanisms, testing, and results, see:

[Project Implementation Report](PROJECT_REPORT.md)