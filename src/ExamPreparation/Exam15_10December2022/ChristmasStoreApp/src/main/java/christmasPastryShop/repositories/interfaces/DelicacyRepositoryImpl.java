package christmasPastryShop.repositories.interfaces;

import christmasPastryShop.entities.delicacies.interfaces.Delicacy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class DelicacyRepositoryImpl implements DelicacyRepository<Delicacy> {
    private Collection<Delicacy> delicacies = new ArrayList<>();
    @Override
    public Delicacy getByName(String name) {
        return this.delicacies
                .stream()
                .filter(e -> e.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Collection<Delicacy> getAll() {
        return Collections.unmodifiableCollection(this.delicacies);
    }

    @Override
    public void add(Delicacy delicacy) {
        this.delicacies.add(delicacy);
    }
}
