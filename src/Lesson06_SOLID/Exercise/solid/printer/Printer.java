package src.Lesson06_SOLID.Exercise.solid.printer;

import src.Lesson06_SOLID.Exercise.solid.calculators.Calculator;
import src.Lesson06_SOLID.Exercise.solid.products.Product;

import java.util.List;

public class Printer {
    private Calculator calculator;
    private static final String SUM = "Sum: %f";
    private static final String AVERAGE = "Average: %f";
    public void printSum(List<Product> products) {
        System.out.printf((SUM) + "%n", calculator.sum(products));
    }

    public void printAverage(List<Product> products) {
        System.out.printf((AVERAGE) + "%n", calculator.average(products));
    }
}
