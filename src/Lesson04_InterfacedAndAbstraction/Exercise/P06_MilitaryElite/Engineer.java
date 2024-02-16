package Lesson04_InterfacedAndAbstraction.Exercise.P06_MilitaryElite;

import java.util.Collection;

public interface Engineer extends SpecialisedSoldier {

    Collection<RepairImpl> getRepairs();

    void addRepair(RepairImpl repair);
}