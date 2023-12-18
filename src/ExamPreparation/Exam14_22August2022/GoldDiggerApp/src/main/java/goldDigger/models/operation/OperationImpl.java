package goldDigger.models.operation;


import goldDigger.models.discoverer.BaseDiscoverer;
import goldDigger.models.discoverer.Discoverer;
import goldDigger.models.spot.Spot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OperationImpl implements Operation {
    public OperationImpl() {
    }

    @Override
    public void startOperation(Spot spot, Collection<Discoverer> discoverers) {
        List<String> spotExhibits = new ArrayList<>(spot.getExhibits());

        for (Discoverer discoverer : discoverers) {

            while (discoverer.canDig() && !spotExhibits.isEmpty()) {

                discoverer.getMuseum().getExhibits().add(spotExhibits.get(0));

                spot.getExhibits().remove(spotExhibits.get(0));

                spotExhibits.remove(0);

                discoverer.dig();
            }
        }
    }
}
