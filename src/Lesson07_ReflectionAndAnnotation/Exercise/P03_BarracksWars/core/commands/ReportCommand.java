package Lesson06_ReflectionAndAnnotation.Exercise.P03_BarracksWars.core.commands;

import Lesson06_ReflectionAndAnnotation.Exercise.P03_BarracksWars.annotations.Inject;
import Lesson06_ReflectionAndAnnotation.Exercise.P03_BarracksWars.interfaces.Repository;

public class ReportCommand extends Command {

    @Inject
    private Repository repository;

    public ReportCommand(String[] data/*, Repository repository, UnitFactory unitFactory*/) {
        super(data/*, repository, unitFactory*/);
    }

    @Override
    public String execute() {
        return repository.getStatistics();
    }
}
