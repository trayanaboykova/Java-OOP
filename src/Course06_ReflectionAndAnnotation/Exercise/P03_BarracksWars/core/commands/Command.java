package Course06_ReflectionAndAnnotation.Exercise.P03_BarracksWars.core.commands;

import Course06_ReflectionAndAnnotation.Exercise.P03_BarracksWars.interfaces.Executable;

public abstract class Command implements Executable {

    private String[] data;


    protected Command(String[] data) {
        this.data = data;
        /*this.repository = repository;
        this.unitFactory = unitFactory;*/
    }

    protected String[] getData() {
        return data;
    }

/*    protected Repository getRepository() {
        return repository;
    }

    protected UnitFactory getUnitFactory() {
        return unitFactory;
    }*/
}
