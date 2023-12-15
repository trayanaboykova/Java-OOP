package aquarium.entities.aquariums;

import aquarium.entities.decorations.Decoration;
import aquarium.entities.fish.Fish;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static aquarium.common.ConstantMessages.*;
import static aquarium.common.ExceptionMessages.*;

public abstract class BaseAquarium implements Aquarium {
    private String name;
    private int capacity;
    private Collection<Decoration> decorations;
    private Collection<Fish> fish;

    protected BaseAquarium(String name, int capacity) {
        setName(name);
        this.capacity = capacity;
        this.decorations = new ArrayList<>();
        this.fish = new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(AQUARIUM_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int calculateComfort() {
        int totalComfort = 0;
        for (Decoration decoration : decorations) {
            totalComfort += decoration.getComfort();
        }
        return totalComfort;
    }

    @Override
    public void addFish(Fish fish) {
        if (this.fish.size() >= capacity) {
            throw new IllegalStateException(NOT_ENOUGH_CAPACITY);
        }
        this.fish.add(fish);
    }

    @Override
    public void removeFish(Fish fish) {
        this.fish.remove(fish);
    }

    @Override
    public void addDecoration(Decoration decoration) {
        decorations.add(decoration);
    }

    @Override
    public void feed() {
        for (Fish fish : fish) {
            fish.eat();
        }
    }

    @Override
    public String getInfo() {
        StringBuilder info = new StringBuilder();
        info.append(String.format("%s (%s):\n", name, this.getClass().getSimpleName()));
        info.append("Fish: ");
        if (fish.isEmpty()) {
            info.append("none");
        } else {
            List<String> fishNames = new ArrayList<>();
            for (Fish f : fish) {
                fishNames.add(f.getName());
            }
            Collections.sort(fishNames);
            info.append(String.join(" ", fishNames));
        }
        info.append("\nDecorations: ").append(decorations.size()).append("\n");
        info.append("Comfort: ").append(calculateComfort());
        return info.toString();
    }

    @Override
    public Collection<Fish> getFish() {
        return null;
    }

    @Override
    public Collection<Decoration> getDecorations() {
        return null;
    }
}
