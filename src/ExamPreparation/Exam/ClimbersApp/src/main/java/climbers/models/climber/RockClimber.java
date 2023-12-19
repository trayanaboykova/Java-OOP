package climbers.models.climber;

public class RockClimber extends BaseClimber {
    private static final double INITIAL_STRENGTH = 120;
    public RockClimber(String name) {
        super(name, INITIAL_STRENGTH);
    }

    @Override
    public void climb() {
        if (super.getStrength() < 0) {
            super.setStrength(0);
        } else {
            super.setStrength(super.getStrength()- 60);
        }
    }
}
