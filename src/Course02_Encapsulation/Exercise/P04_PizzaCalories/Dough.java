package Course02_Encapsulation.Exercise.P04_PizzaCalories;

import java.util.Arrays;

public class Dough {
    private FlourType flourType;
    private BakingTechnique bakingTechnique;
    private double weight;

    public Dough(String flourType, String bakingTechnique, double weight) {
        setFlourType(flourType);
        setBakingTechnique(bakingTechnique);
        setWeight(weight);
    }

    private void setFlourType(String flourType) {
        boolean isFlourNotExist = Arrays.stream(FlourType.values()).noneMatch(e -> e.name().equals(flourType));
        if (isFlourNotExist) {
            errorMessage();
        }
        this.flourType = FlourType.valueOf(flourType);
    }

    private void setBakingTechnique(String bakingTechnique) {
        boolean isTechniqueNotExist = Arrays.stream(BakingTechnique.values()).noneMatch(e -> e.name().equals(bakingTechnique));
        if (isTechniqueNotExist) {
            errorMessage();
        }
        this.bakingTechnique = BakingTechnique.valueOf(bakingTechnique);
    }

    private void setWeight(double weight) {
        if (weight < 1 || weight > 200) {
            throw new IllegalArgumentException("Dough weight should be in the range [1..200].");
        }
        this.weight = weight;
    }

    public double calculateCalories() {
        return (2 * weight) * flourType.getModifier() /*getFlourTypeModifier()*/ * bakingTechnique.getModifier() /*getBakingTechniqueModifier()*/;
    }

    private static void errorMessage() {
        throw new IllegalArgumentException("Invalid type of dough.");
    }
}
