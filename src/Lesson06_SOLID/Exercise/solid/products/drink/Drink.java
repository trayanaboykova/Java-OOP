package src.Lesson06_SOLID.Exercise.solid.products.drink;

import src.Lesson06_SOLID.Exercise.solid.products.Product;

public interface Drink extends Product {
    double getLiters();
    double getDensity();
}
