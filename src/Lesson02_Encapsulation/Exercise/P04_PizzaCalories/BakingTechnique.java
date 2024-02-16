package Lesson02_Encapsulation.Exercise.P04_PizzaCalories;

public enum BakingTechnique {
    Crispy(0.9),
    Chewy(1.1),
    Homemade(1.0);

    private double modifier;

    BakingTechnique(double modifier) {
        this.modifier = modifier;
    }

    public double getModifier() {
        return modifier;
    }
}
