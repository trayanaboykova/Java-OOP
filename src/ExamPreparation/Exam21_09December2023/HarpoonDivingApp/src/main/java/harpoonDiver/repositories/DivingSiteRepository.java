package harpoonDiver.repositories;

import harpoonDiver.models.diving.Diving;
import harpoonDiver.models.divingSite.DivingSite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class DivingSiteRepository<T> implements Repository<DivingSite> {
    private Collection<DivingSite> divingSites;

    public DivingSiteRepository() {
        this.divingSites = new ArrayList<>();
    }

    @Override
    public Collection<DivingSite> getCollection() {
        return Collections.unmodifiableCollection(divingSites);
    }

    @Override
    public void add(DivingSite divingSite) {
        divingSites.add(divingSite);
    }

    @Override
    public boolean remove(DivingSite divingSite) {
        return divingSites.remove(divingSite);
    }

    @Override
    public DivingSite byName(String name) {
        return divingSites.stream()
                .filter(d->d.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}