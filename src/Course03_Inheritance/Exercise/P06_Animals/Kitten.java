package Course03_Inheritance.Exercise.P06_Animals;

public class Kitten extends Cat {

    private final static String DEFAULT_GENDER = "Female";

    public Kitten(String name, int age) {
        super(name, age, DEFAULT_GENDER);
    }

    @Override
    public String produceSound() {
        return "Meow";
    }
}