package Lesson05_Polymorphism.Lab.P03_Animals;

public class Cat extends Animal {
    public Cat(String name, String favouriteFood) {

        super(name, favouriteFood);
    }

    @Override
    public String explainSelf() {

        return String.format("%s\nMEEOW", super.explainSelf());
    }
}
