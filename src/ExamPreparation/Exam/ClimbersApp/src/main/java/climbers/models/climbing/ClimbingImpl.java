package climbers.models.climbing;

import climbers.models.climber.Climber;
import climbers.models.mountain.Mountain;

import java.util.Collection;

public class ClimbingImpl implements Climbing {

    @Override
    public void conqueringPeaks(Mountain mountain, Collection<Climber> climbers) {
        Collection<String> mountainPeaks = mountain.getPeaksList();

        for (Climber climber : climbers) {
            while (climber.canClimb() && mountainPeaks.iterator().hasNext()) {
                climber.climb();

                String currentPeak = mountainPeaks.iterator().next();
                climber.getRoster().getPeaks().add(currentPeak);
                mountainPeaks.remove(currentPeak);
            }
        }
    }
}
