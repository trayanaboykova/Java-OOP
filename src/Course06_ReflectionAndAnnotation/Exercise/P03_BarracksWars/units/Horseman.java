package Course06_ReflectionAndAnnotation.Exercise.P03_BarracksWars.units;

public class Horseman extends AbstractUnit {

    private static final int HORSEMAN_HEALTH = 50;
    private static final int HORSEMAN_DAMAGE = 10;

    public Horseman() {
        super(HORSEMAN_HEALTH, HORSEMAN_DAMAGE);
    }
}
