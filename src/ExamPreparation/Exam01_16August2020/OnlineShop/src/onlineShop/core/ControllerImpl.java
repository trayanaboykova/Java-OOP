package onlineShop.core;

import onlineShop.core.interfaces.Controller;
import onlineShop.models.products.components.*;
import onlineShop.models.products.computers.*;
import onlineShop.models.products.peripherals.*;
import java.util.LinkedHashMap;
import java.util.Map;

import static onlineShop.common.constants.ExceptionMessages.*;
import static onlineShop.common.constants.OutputMessages.*;

public class ControllerImpl implements Controller {
    private Map<Integer, Computer> computers;
    private Computer computer;

    public ControllerImpl() {
        this.computers = new LinkedHashMap<>();
    }

    @Override
    public String addComputer(String computerType, int id, String manufacturer, String model, double price) {
        if (computers.containsKey(id)) {
            throw new IllegalArgumentException(EXISTING_COMPUTER_ID);
        }

        Computer computer;
        if (computerType.equals("DesktopComputer")) {
            computer = new DesktopComputer(id, manufacturer, model, price);
        } else if (computerType.equals("Laptop")) {
            computer = new Laptop(id, manufacturer, model, price);
        } else {
            throw new IllegalArgumentException(INVALID_COMPUTER_TYPE);
        }

        computers.put(id, computer);

        return String.format(ADDED_COMPUTER, id);
    }

    @Override
    public String addComponent(int computerId, int id, String componentType, String manufacturer, String model, double price, double overallPerformance, int generation) {
        if (!computers.containsKey(computerId)) {
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }
        Computer computer = computers.get(computerId);
        for (Component existingComponent : computer.getComponents()) {
            if (existingComponent.getId() == id) {
                throw new IllegalArgumentException(EXISTING_COMPONENT_ID);
            }
        }
        Component component;
        switch (componentType) {
            case "CentralProcessingUnit":
                component = new CentralProcessingUnit(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "Motherboard":
                component = new Motherboard(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "PowerSupply":
                component = new PowerSupply(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "RandomAccessMemory":
                component = new RandomAccessMemory(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "SolidStateDrive":
                component = new SolidStateDrive(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "VideoCard":
                component = new VideoCard(id, manufacturer, model, price, overallPerformance, generation);
                break;
            default:
                throw new IllegalArgumentException(INVALID_COMPONENT_TYPE);
        }

        computers.get(computerId).addComponent(component);

        return String.format(ADDED_COMPONENT, componentType, id, computerId);
    }

    @Override
    public String removeComponent(String componentType, int computerId) {
        if (!computers.containsKey(computerId)) {
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }
        Component component = computers.get(computerId).removeComponent(componentType);
        if (component == null) {
            return String.format(NOT_EXISTING_COMPONENT, componentType, computers
                    .get(computerId)
                    .getClass()
                    .getSimpleName(), computerId);
        }

        return String.format(REMOVED_COMPONENT, componentType, component.getId());
    }

    @Override
    public String addPeripheral(int computerId, int id, String peripheralType, String manufacturer, String model, double price, double overallPerformance, String connectionType) {
        if (!computers.containsKey(computerId)){
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }
        Computer computer = computers.get(computerId);
        for (Peripheral existingPeripheral : computer.getPeripherals()) {
            if (existingPeripheral.getId() == id) {
                throw new IllegalArgumentException(EXISTING_PERIPHERAL_ID);
            }
        }
        Peripheral peripheral;
        switch (peripheralType) {
            case "Headset":
                peripheral = new Headset(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Keyboard":
                peripheral = new Keyboard(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Monitor":
                peripheral = new Monitor(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Mouse":
                peripheral = new Mouse(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            default:
                throw new IllegalArgumentException(INVALID_PERIPHERAL_TYPE);
        }
        computers.get(computerId).addPeripheral(peripheral);
        return String.format(ADDED_PERIPHERAL, peripheralType, peripheral.getId(), computerId);
    }

    @Override
    public String removePeripheral(String peripheralType, int computerId) {
        if (!computers.containsKey(computerId)) {
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }
        Peripheral peripheral = computers.get(computerId).removePeripheral(peripheralType);
        if (peripheral == null) {
            return String.format(NOT_EXISTING_PERIPHERAL, peripheralType, computers
                    .get(computerId)
                    .getClass()
                    .getSimpleName(), computerId);
        }

        return String.format(REMOVED_PERIPHERAL, peripheralType, peripheral.getId());
    }

    @Override
    public String buyComputer(int id) {
        if (!computers.containsKey(id)) {
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }
        Computer computer = computers.get(id);
        computers.remove(id);
        return computer.toString();
    }

    @Override
    public String BuyBestComputer(double budget) {
        Computer bestComputer = null;
        double bestPerformance = 0;
        for (Computer computer : computers.values()) {
            double computerPrice = computer.getPrice();
            double computerPerformance = computer.getOverallPerformance();
            if (computerPrice <= budget && computerPerformance > bestPerformance) {
                bestComputer = computer;
                bestPerformance = computerPerformance;
            }
        }
        if (bestComputer == null) {
            throw new IllegalArgumentException(String.format(CAN_NOT_BUY_COMPUTER, budget));
        }
        computers.remove(bestComputer.getId());
        return bestComputer.toString();
    }

    @Override
    public String getComputerData(int id) {
        if (!computers.containsKey(id)) {
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }
        Computer computer = computers.get(id);
        return computer.toString();
    }
}
