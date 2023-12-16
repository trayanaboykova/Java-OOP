package spaceStation.models.astronauts;

public class Biologist extends BaseAstronaut {
    private static final double INITIAL_OXYGEN_UNITS = 70;
    public Biologist(String name) {
        super(name, INITIAL_OXYGEN_UNITS);
    }

    @Override
    public void breath() {
        this.setOxygen(this.getOxygen() - 5);    }
}
