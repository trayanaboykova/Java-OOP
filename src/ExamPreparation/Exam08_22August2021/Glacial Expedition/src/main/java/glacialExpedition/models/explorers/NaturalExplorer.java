package glacialExpedition.models.explorers;

public class NaturalExplorer extends BaseExplorer {
    private static final double INITIAL_ENERGY_UNITS = 60;
    public NaturalExplorer(String name) {
        super(name, INITIAL_ENERGY_UNITS);
    }

    @Override
    public void search() {
        if (getEnergy() - 7 < 0) {
            setEnergy(0);
        } else {
            setEnergy(getEnergy() - 7);
        }
    }
}
