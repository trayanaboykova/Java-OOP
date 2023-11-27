package Course05_Polymorphism.Lab.P03_Animals;

public class Dog extends Animal {

    public Dog(String name, String favouriteFood) {

        super(name, favouriteFood);
    }

    @Override
    public String explainSelf() {

        return String.format("%s\nDJAAF\n", super.explainSelf());
    }
}
