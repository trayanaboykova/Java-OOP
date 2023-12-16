package spaceStation.models.mission;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.bags.Bag;
import spaceStation.models.planets.Planet;

import java.util.Collection;
import java.util.stream.Collectors;

public class MissionImpl implements Mission {
    public MissionImpl() {
    }
    @Override
    public void explore(Planet planet, Collection<Astronaut> astronauts) {
        for (Astronaut astronaut : astronauts) {
            Bag bag = astronaut.getBag();
            while (astronaut.canBreath() && !planet.getItems().isEmpty()) {
                String item = planet.getItems().stream().collect(Collectors.toList()).get(0);
                astronaut.breath();
                bag.getItems().add(item);
                planet.getItems().remove(item);
            }
        }
    }
}
