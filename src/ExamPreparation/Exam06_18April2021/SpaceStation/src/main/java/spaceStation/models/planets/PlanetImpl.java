package spaceStation.models.planets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static spaceStation.common.ExceptionMessages.*;

public class PlanetImpl implements Planet {
    private String name;
    private Collection<String> items;

    public PlanetImpl(String name, String... items) {
        this.setName(name);
        this.setItems(items);
    }

    private void setItems(String[] items) {
        this.items = new ArrayList<>();
        this.items.addAll(Arrays.asList(items));
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(PLANET_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public Collection<String> getItems() {
        return this.items;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
