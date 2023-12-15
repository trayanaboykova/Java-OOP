package onlineShop.models.products;

import static onlineShop.common.constants.ExceptionMessages.*;
import static onlineShop.common.constants.OutputMessages.*;

public abstract class BaseProduct implements Product {
    private int id;
    private String manufacturer;
    private String model;
    private double price;
    private double overallPerformance;

    public BaseProduct(int id, String manufacturer, String model, double price, double overallPerformance) {
        if (id <= 0) {
            throw new IllegalArgumentException(INVALID_PRODUCT_ID);
        }
        if (manufacturer == null || manufacturer.trim().isEmpty()) {
            throw new IllegalArgumentException(INVALID_MANUFACTURER);
        }
        if (model == null || model.trim().isEmpty()) {
            throw new IllegalArgumentException(INVALID_MODEL);
        }
        if (price <= 0) {
            throw new IllegalArgumentException(INVALID_PRICE);
        }
        if (overallPerformance <= 0) {
            throw new IllegalArgumentException(INVALID_OVERALL_PERFORMANCE);
        }

        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.price = price;
        this.overallPerformance = overallPerformance;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getManufacturer() {
        return manufacturer;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public double getOverallPerformance() {
        return overallPerformance;
    }

    @Override
    public String toString() {
        return String.format(PRODUCT_TO_STRING,
                overallPerformance, price, getClass().getSimpleName(), manufacturer, model, id);
    }
}
