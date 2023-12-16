package spaceStation.models.astronauts;

public class Geodesist extends BaseAstronaut {
    private static final double INITIAL_OXYGEN_UNITS = 50;
    public Geodesist(String name) {
        super(name, INITIAL_OXYGEN_UNITS);
    }
}
