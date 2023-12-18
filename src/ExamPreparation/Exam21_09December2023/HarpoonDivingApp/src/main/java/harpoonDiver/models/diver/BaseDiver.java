package harpoonDiver.models.diver;

import harpoonDiver.models.seaCatch.BaseSeaCatch;
import harpoonDiver.models.seaCatch.SeaCatch;

import static harpoonDiver.common.ExceptionMessages.*;

public abstract class BaseDiver implements Diver {
    private String name;
    private double oxygen;
    private SeaCatch seaCatch;

    public BaseDiver(String name, double oxygen) {
        setName(name);
        setOxygen(oxygen);
        this.seaCatch = new BaseSeaCatch();
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new NullPointerException(DIVER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public void setOxygen(double oxygen) {
        if (oxygen < 0) {
            throw new IllegalArgumentException(DIVER_OXYGEN_LESS_THAN_ZERO);
        }
        this.oxygen = oxygen;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getOxygen() {
        return oxygen;
    }

    @Override
    public boolean canDive() {
        return getOxygen() > 0;
    }

    @Override
    public SeaCatch getSeaCatch() {
        return seaCatch;
    }

    @Override
    public void shoot() {
        if (getOxygen() < 0) {
            setOxygen(0);
        }
        setOxygen(getOxygen() - 30);
    }
}