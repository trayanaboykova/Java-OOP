package easterRaces.entities.drivers;

import easterRaces.entities.cars.Car;

import static easterRaces.common.ExceptionMessages.*;

public class DriverImpl implements Driver {
    private String name;
    private Car car;
    private int numberOfWins;
    private boolean canParticipate;

    public DriverImpl(String name) {
        if (name == null || name.trim().length() < 5) {
            throw new IllegalArgumentException(INVALID_NAME + name);
        }
        this.name = name;
        this.canParticipate = false;
    }

    public void addCar(Car car) {
        if (car == null) {
            throw new IllegalArgumentException(CAR_INVALID);
        }
        this.car = car;
        this.canParticipate = true;
    }

    public void winRace() {
        numberOfWins++;
    }

    public String getName() {
        return name;
    }

    public Car getCar() {
        return car;
    }

    public int getNumberOfWins() {
        return numberOfWins;
    }

    @Override
    public boolean getCanParticipate() {
        return car != null;
    }
}