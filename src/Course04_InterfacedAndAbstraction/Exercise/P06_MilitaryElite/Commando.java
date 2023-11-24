package Course04_InterfacedAndAbstraction.Exercise.P06_MilitaryElite;

import java.util.Collection;

public interface Commando extends SpecialisedSoldier {

    Collection<MissionImpl> getMission();

    void addMission(MissionImpl mission);
}