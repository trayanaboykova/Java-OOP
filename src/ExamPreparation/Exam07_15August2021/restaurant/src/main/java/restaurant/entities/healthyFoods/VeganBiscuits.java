package restaurant.entities.healthyFoods;

public class VeganBiscuits extends Food {
    private static final double initialVeganBiscuitPortion = 205;

    public VeganBiscuits(String name, double price) {
        super(name, initialVeganBiscuitPortion, price);
    }
}
