package com.ga.concurrentcsvprocessor.testing;

import com.ga.concurrentcsvprocessor.calculator.SalaryCalculator;

import java.time.LocalDate;

public class SalaryCalculatorTest {

    public static void main(String[] args) {

        SalaryCalculator calculator = new SalaryCalculator();

        double result = calculator.calculateFinalIncrease(0.5, 5, 10);

        System.out.println("Test 1 - Expected: 0");
        System.out.println("Actual: " + result);


        double result2 = calculator.calculateFinalIncrease(0.6, 5, 10);

        System.out.println("Test 2 - Expected: 15");
        System.out.println("Actual: " + result2);



        double result3 = calculator.calculateFinalIncrease(0.8, 5, 10);

        System.out.println("Test 3 - Expected: 15");
        System.out.println("Actual: " + result3);

        double result4 = calculator.calculateFinalIncrease(0.9, 5, 10);

        System.out.println("Test 4 - Expected: 22.5");
        System.out.println("Actual: " + result4);



        LocalDate joinedDate1 = LocalDate.now().minusMonths(11);

        double result5 = calculator.calculateYearsIncrease(joinedDate1);

        System.out.println("Test 5 - Expected: 0");
        System.out.println("Actual: " + result5);


        LocalDate joinedDate2 = LocalDate.now().minusYears(1);

        double result6 = calculator.calculateYearsIncrease(joinedDate2);

        System.out.println("Test 6 - Expected: 2");
        System.out.println("Actual: " + result6);



        LocalDate joinedDate3 = LocalDate.now().minusYears(2);

        double result7 = calculator.calculateYearsIncrease(joinedDate3);

        System.out.println("Test 7 - Expected: 4");
        System.out.println("Actual: " + result7);
    }


}
