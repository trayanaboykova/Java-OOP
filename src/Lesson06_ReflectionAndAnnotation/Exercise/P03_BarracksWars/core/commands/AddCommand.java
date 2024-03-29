package Lesson06_ReflectionAndAnnotation.Exercise.P03_BarracksWars.core.commands;

import Lesson06_ReflectionAndAnnotation.Exercise.P03_BarracksWars.annotations.Inject;
import Lesson06_ReflectionAndAnnotation.Exercise.P03_BarracksWars.interfaces.Repository;
import Lesson06_ReflectionAndAnnotation.Exercise.P03_BarracksWars.interfaces.Unit;
import Lesson06_ReflectionAndAnnotation.Exercise.P03_BarracksWars.interfaces.UnitFactory;

public class AddCommand extends Command {

    @Inject
    private Repository repository;
    @Inject
    private UnitFactory unitFactory;

    public AddCommand(String[] data) {
        super(data/*, repository, unitFactory*/);
    }

    @Override
    public String execute() {

        String unitType = getData()[1];

        Unit unitToAdd = unitFactory.createUnit(unitType);

        repository.addUnit(unitToAdd);

        return unitType + " added!";
    }
}
