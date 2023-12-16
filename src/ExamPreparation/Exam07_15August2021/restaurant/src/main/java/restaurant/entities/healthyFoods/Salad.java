package restaurant.entities.healthyFoods;

public class Salad extends Food {
    private static final double initialSaladPortion = 150;

    public Salad(String name, double price) {
        super(name, initialSaladPortion, price);
    }
}
