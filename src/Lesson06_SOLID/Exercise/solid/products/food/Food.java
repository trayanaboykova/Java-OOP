package src.Lesson06_SOLID.Exercise.solid.products.food;

import src.Lesson06_SOLID.Exercise.solid.products.Product;

public interface Food extends Product {
    double getCalories();

    double getKilograms();
}
