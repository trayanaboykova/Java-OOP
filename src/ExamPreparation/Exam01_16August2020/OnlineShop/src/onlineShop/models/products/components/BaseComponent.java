package onlineShop.models.products.components;

import onlineShop.models.products.BaseProduct;

import static onlineShop.common.constants.OutputMessages.*;

public abstract class BaseComponent extends BaseProduct implements Component {
    private int generation;

    public BaseComponent(int id, String manufacturer, String model, double price, double overallPerformance, int generation) {
        super(id, manufacturer, model, price, overallPerformance);

        this.generation = generation;
    }

    public int getGeneration() {
        return generation;
    }

    @Override
    public String toString() {
        return String.format(super.toString() + COMPONENT_TO_STRING,
                getOverallPerformance(), getPrice(), getClass().getSimpleName(), getManufacturer(), getModel(), getId(), generation);
    }
}
