package src.Lesson06_SOLID.Exercise.solid.calculators;

import src.Lesson06_SOLID.Exercise.solid.products.Product;
import src.Lesson06_SOLID.Exercise.solid.products.drink.Drink;
import src.Lesson06_SOLID.Exercise.solid.products.food.Food;

import java.util.List;

public class QuantityCalculatorImpl implements Calculator {

    @Override
    public double sum (List<Product> products) {
        //сума от количества
        double sum = 0;
        for (Product product : products) {
            //product: Drink или Food
            if (product instanceof Drink drink) {
                sum += drink.getLiters() * drink.getDensity();
            } else if (product instanceof Food food) {
                sum += food.getKilograms();
            }
        }

        return sum;
    }

    @Override
    public double average (List<Product> products) {
        return sum(products) / products.size();
    }
}