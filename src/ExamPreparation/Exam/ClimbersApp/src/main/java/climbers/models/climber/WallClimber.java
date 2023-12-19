package climbers.models.climber;

public class WallClimber extends BaseClimber {
    private static final double INITIAL_STRENGTH = 90;
    public WallClimber(String name) {
        super(name, INITIAL_STRENGTH);
    }

    @Override
    public void climb() {
        if (super.getStrength() < 0) {
            super.setStrength(0);
        } else {
            super.setStrength(super.getStrength()- 30);
        }
    }
}
