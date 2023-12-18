package vehicleShop.repositories;

import vehicleShop.models.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class VehicleRepository implements Repository<Vehicle> {
    private List<Vehicle> vehicles = new ArrayList<>();

    @Override
    public Collection<Vehicle> getWorkers() {
        return Collections.unmodifiableList(this.vehicles);
    }

    @Override
    public void add(Vehicle model) {
        vehicles.add(model);
    }

    @Override
    public boolean remove(Vehicle model) {
        return vehicles.remove(model);
    }

    @Override
    public Vehicle findByName(String name) {
        return vehicles.stream()
                .filter(w -> w.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
