package harpoonDiver.models.diving;

import harpoonDiver.models.diver.Diver;
import harpoonDiver.models.divingSite.DivingSite;

import java.util.Collection;

public class DivingImpl implements Diving {
    @Override
    public void searching(DivingSite divingSite, Collection<Diver> divers) {

        Collection<String> divingSiteSeaCreatures = divingSite.getSeaCreatures();

        for (Diver diver : divers) {
            while (diver.canDive() && divingSiteSeaCreatures.iterator().hasNext()) {
                diver.shoot();

                String currentCreature = divingSiteSeaCreatures.iterator().next();
                diver.getSeaCatch().getSeaCreatures().add(currentCreature);
                divingSiteSeaCreatures.remove(currentCreature);
            }
        }
    }
}