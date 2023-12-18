package robotService.entities.supplements;

import robotService.entities.supplements.BaseSupplement;

public class PlasticArmor extends BaseSupplement {

    private static final int hardness = 1;
    private static final double price = 10;

    public PlasticArmor() {
        super(hardness, price);
    }
}