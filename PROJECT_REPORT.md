# Concurrent CSV Data Processor
## Project Implementation Report

### 1. Introduction

The Concurrent CSV Data Processor is a Java Spring Boot application developed to read employee records from a CSV file and calculate updated salaries.

The main purpose of the project is to apply salary increase rules while using multithreading to process employee records concurrently.

The application demonstrates Java concurrency concepts, including thread pooling, locks, semaphores, and atomic variables.

### 2. Project Objectives

The objectives of this project are:

- Read employee information from a CSV file.
- Validate employee records before processing.
- Calculate salary increases based on employee roles, completed years of service, and project completion percentages.
- Process employee records concurrently using a fixed thread pool.
- Protect shared results using concurrency control mechanisms.
- Test the application and verify the calculated results.

### 3. Tools and Technologies

The following technologies were used:

- **Java:** Core programming language.
- **Spring Boot:** Application framework.
- **Maven:** Project build and dependency management.
- **ExecutorService:** Managing the thread pool.
- **Runnable:** Defining employee processing tasks.
- **ReentrantLock:** Protecting shared employee results.
- **Semaphore:** Limiting concurrent salary calculations.
- **AtomicInteger:** Counting successfully processed employees.
- **IntelliJ IDEA:** Development environment.
- **Git and GitHub:** Version control.

### 4. Application Implementation

The application reads employee data from the provided CSV file, which contains 30 employee records.

Each CSV record includes an employee ID, name, current salary, joining date, company role, and completed projects percentage.

The data is converted into Employee objects and validated before salary calculations begin.

The SalaryCalculator class applies the salary increase rules. Employees who completed less than 60% of their projects do not receive an increase. Eligible employees receive an increase based on their role and completed years of service.

An additional bonus multiplier of 1.5 is applied when an employee has completed more than 80% of their projects.

The application uses a fixed thread pool with four threads. Each EmployeeTask calculates the updated salary of one employee and stores the result in a shared ResultCollector.

A ReentrantLock protects the results list, while a Semaphore limits salary calculations to two tasks at a time. An AtomicInteger counts the successfully stored results.


# Concurrent CSV Data Processor
## Project Implementation Report

### 1. Introduction

The Concurrent CSV Data Processor is a Java Spring Boot application developed to read employee records from a CSV file and calculate updated salaries.

The main purpose of the project is to apply salary increase rules while using multithreading to process employee records concurrently.

The application demonstrates Java concurrency concepts, including thread pooling, locks, semaphores, and atomic variables.

### 2. Project Objectives

The objectives of this project are:

- Read employee information from a CSV file.
- Validate employee records before processing.
- Calculate salary increases based on employee roles, completed years of service, and project completion percentages.
- Process employee records concurrently using a fixed thread pool.
- Protect shared results using concurrency control mechanisms.
- Test the application and verify the calculated results.

### 3. Tools and Technologies

The following technologies were used:

- **Java:** Core programming language.
- **Spring Boot:** Application framework.
- **Maven:** Project build and dependency management.
- **ExecutorService:** Managing the thread pool.
- **Runnable:** Defining employee processing tasks.
- **ReentrantLock:** Protecting shared employee results.
- **Semaphore:** Limiting concurrent salary calculations.
- **AtomicInteger:** Counting successfully processed employees.
- **IntelliJ IDEA:** Development environment.
- **Git and GitHub:** Version control.

### 4. Application Implementation

The application reads employee data from the provided CSV file, which contains 30 employee records.

Each CSV record includes an employee ID, name, current salary, joining date, company role, and completed projects percentage.

The data is converted into Employee objects and validated before salary calculations begin.

The SalaryCalculator class applies the salary increase rules. Employees who completed less than 60% of their projects do not receive an increase. Eligible employees receive an increase based on their role and completed years of service.

An additional bonus multiplier of 1.5 is applied when an employee has completed more than 80% of their projects.

The application uses a fixed thread pool with four threads. Each EmployeeTask calculates the updated salary of one employee and stores the result in a shared ResultCollector.

A ReentrantLock protects the results list, while a Semaphore limits salary calculations to two tasks at a time. An AtomicInteger counts the successfully stored results.

### 5. Testing

The application was tested using the provided CSV file containing 30 employee records.

The salary calculation logic was also tested using seven test cases covering:

- Project completion below 60%.
- Project completion at exactly 60%.
- Project completion at exactly 80%.
- Project completion above 80%.
- Employment duration below one year.
- One completed year of service.
- Two completed years of service.

All seven tests produced the expected results.

### 6. Results

The application successfully processed all 30 employee records from the provided CSV file.

The final console output confirmed:

- Total employees: 30
- Processed employees: 30
- Atomic counter: 30
- All employee salaries calculated successfully

Four worker threads were used to process the employee records. The order of employee results varied because tasks were executed concurrently.

The application also displayed the original salary, calculated salary, and thread name for each employee.

### 7. Challenges and Solutions

**Concurrent access to shared data**

Multiple threads needed to store results in one shared list. A ReentrantLock was used to protect the list during additions and reads.

**Limiting concurrent operations**

A Semaphore with two permits was used to control how many tasks could enter the salary calculation section simultaneously.

**Processing invalid employee data**

The EmployeeValidator class checks employee information before processing. Invalid CSV rows are reported so the reader can continue processing other records.

**Tracking completed work**

An AtomicInteger was used to count successfully stored employee results.

### 8. Conclusion

The Concurrent CSV Data Processor demonstrates how Java multithreading can be used to process employee records while maintaining safe access to shared data.

The project combines CSV file reading, salary calculation rules, thread pooling, locks, semaphores, and atomic variables.

The application processed all 30 employee records in the provided sample file, and the seven salary calculation test cases produced the expected results.

### 9. Future Improvements

Future improvements could include a frontend interface, interactive salary calculations, and support for uploading different CSV files.