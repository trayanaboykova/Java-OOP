package vehicleShop.repositories;

import vehicleShop.models.worker.Worker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class WorkerRepository implements Repository<Worker> {
    private List<Worker> workers = new ArrayList<>();

    @Override
    public Collection<Worker> getWorkers() {
        return Collections.unmodifiableList(this.workers);
    }

    @Override
    public void add(Worker worker) {
        workers.add(worker);
    }

    @Override
    public boolean remove(Worker worker) {
        return workers.remove(worker);
    }

    @Override
    public Worker findByName(String name) {
        return workers.stream()
                .filter(w -> w.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
