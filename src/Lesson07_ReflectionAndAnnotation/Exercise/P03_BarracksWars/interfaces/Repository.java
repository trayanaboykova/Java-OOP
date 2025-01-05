package Lesson06_ReflectionAndAnnotation.Exercise.P03_BarracksWars.interfaces;

import jdk.jshell.spi.ExecutionControl;

public interface Repository {
    void addUnit(Unit unit);

    String getStatistics();

    void removeUnit(String unitType) throws ExecutionControl.NotImplementedException;
}
