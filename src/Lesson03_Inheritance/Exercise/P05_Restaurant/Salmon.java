package Lesson03_Inheritance.Exercise.P05_Restaurant;

import java.math.BigDecimal;

public class Salmon extends MainDish {

    private final static double SALMON_GRAMS = 22;

    public Salmon(String name, BigDecimal price) {
        super(name, price, SALMON_GRAMS);
    }
}