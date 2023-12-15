package onlineShop.models.products.computers;

import onlineShop.models.products.BaseProduct;
import onlineShop.models.products.components.Component;
import onlineShop.models.products.peripherals.Peripheral;

import java.util.ArrayList;
import java.util.List;

import static onlineShop.common.constants.ExceptionMessages.*;
import static onlineShop.common.constants.OutputMessages.*;

public abstract class BaseComputer extends BaseProduct implements Computer {
    private List<Component> components;
    private List<Peripheral> peripherals;
    public BaseComputer(int id, String manufacturer, String model, double price, double overallPerformance) {
        super(id, manufacturer, model, price, overallPerformance);
        components = new ArrayList<>();
        peripherals = new ArrayList<>();
    }

    @Override
    public double getOverallPerformance() {
        if (components.isEmpty()) {
            return super.getOverallPerformance();
        } else {
            double componentsOverallPerformance = components.stream()
                    .mapToDouble(Component::getOverallPerformance)
                    .average()
                    .orElse(0);
            return super.getOverallPerformance() + componentsOverallPerformance;
        }
    }

    @Override
    public double getPrice() {
        double componentsPrice = components.stream()
                .mapToDouble(Component::getPrice)
                .sum();

        double peripheralsPrice = peripherals.stream()
                .mapToDouble(Peripheral::getPrice)
                .sum();

        return super.getPrice() + componentsPrice + peripheralsPrice;
    }

    @Override
    public void addComponent(Component component) {
        for (Component existingComponent : components) {
            if (existingComponent.getClass().getSimpleName().equals(component.getClass().getSimpleName())) {
                throw new IllegalArgumentException(String.format(EXISTING_COMPONENT, component.getClass().getSimpleName(), getClass().getSimpleName(), getId()));
            }
        }
        components.add(component);
    }

    @Override
    public Component removeComponent(String componentType) {
        for (Component component : components) {
            if (component.getClass().getSimpleName().equals(componentType)) {
                components.remove(component);
                return component;
            }
        }
        throw new IllegalArgumentException(String.format(NOT_EXISTING_COMPONENT, componentType, getClass().getSimpleName(), getId()));
    }

    @Override
    public void addPeripheral(Peripheral peripheral) {
        for (Peripheral existingPeripheral : peripherals) {
            if (existingPeripheral.getClass().getSimpleName().equals(peripheral.getClass().getSimpleName())) {
                throw new IllegalArgumentException(String.format(EXISTING_PERIPHERAL, peripheral.getClass().getSimpleName(), getClass().getSimpleName(), getId()));
            }
        }
        peripherals.add(peripheral);
    }

    @Override
    public Peripheral removePeripheral(String peripheralType) {
        for (Peripheral peripheral : peripherals) {
            if (peripheral.getClass().getSimpleName().equals(peripheralType)) {
                peripherals.remove(peripheral);
                return peripheral;
            }
        }
        throw new IllegalArgumentException(String.format(NOT_EXISTING_PERIPHERAL, peripheralType, getClass().getSimpleName(), getId()));
    }

    @Override
    public List<Component> getComponents() {
        return components;
    }

    @Override
    public List<Peripheral> getPeripherals() {
        return peripherals;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(super.toString(), getOverallPerformance(), getPrice(), getClass().getSimpleName(), getManufacturer(), getModel(), getId()))
                .append(System.lineSeparator());
        sb.append(" ");
        sb.append(String.format(COMPUTER_COMPONENTS_TO_STRING, components.size())).append(System.lineSeparator());

        for (Component component : components) {
            sb.append("  ");
            sb.append(String.format("%s", component.toString())).append(System.lineSeparator());
        }

        sb.append(" ");
        sb.append(String.format(COMPUTER_PERIPHERALS_TO_STRING, peripherals.size(), peripherals.stream().mapToDouble(Peripheral::getOverallPerformance).average().orElse(0)))
                .append(System.lineSeparator());
        for (Peripheral peripheral : peripherals) {
            sb.append("  ");
            sb.append(String.format("%s", peripheral.toString())).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

}
