package robotService.entities.supplements;

import robotService.entities.supplements.BaseSupplement;

public class MetalArmor extends BaseSupplement {

    private static final int hardness = 5;
    private static final double price = 15;

    public MetalArmor() {
        super(hardness, price);
    }
}