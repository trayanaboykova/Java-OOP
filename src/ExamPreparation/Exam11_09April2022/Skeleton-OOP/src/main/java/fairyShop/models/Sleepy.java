package fairyShop.models;

public class Sleepy extends BaseHelper {
    private static final int ENERGY_UNITS = 50;

    public Sleepy(String name) {
        super(name, ENERGY_UNITS);
    }

    @Override
    public void work() {
        if (super.getEnergy() - 15 < 0) {
            super.setEnergy(0);
        } else {
            super.setEnergy(super.getEnergy()-15);
        }
    }
}
