package Lesson06_ReflectionAndAnnotation.Exercise.P03_BarracksWars.core.commands;

import Lesson06_ReflectionAndAnnotation.Exercise.P03_BarracksWars.annotations.Inject;
import Lesson06_ReflectionAndAnnotation.Exercise.P03_BarracksWars.interfaces.Repository;
import jdk.jshell.spi.ExecutionControl;

public class RetireCommand extends Command {

    @Inject
    private Repository repository;

    public RetireCommand(String[] data) {
        super(data/*, repository, unitFactory*/);
    }

    @Override
    public String execute() {
        try {
            String unitType = getData()[1];
            repository.removeUnit(unitType);
            return unitType + " retired!";
        } catch (IllegalArgumentException | ExecutionControl.NotImplementedException e) {
            return e.getMessage();
        }
    }
}
