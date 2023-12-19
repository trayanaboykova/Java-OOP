package climbers.core;

import climbers.models.climber.Climber;
import climbers.models.climber.RockClimber;
import climbers.models.climber.WallClimber;
import climbers.models.climbing.Climbing;
import climbers.models.climbing.ClimbingImpl;
import climbers.models.mountain.Mountain;
import climbers.models.mountain.MountainImpl;
import climbers.repositories.ClimberRepository;
import climbers.repositories.MountainRepository;
import climbers.repositories.Repository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static climbers.common.ConstantMessages.*;
import static climbers.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private Repository<Climber> climberRepository;
    private Repository<Mountain> mountainRepository;
    private int count;

    public ControllerImpl() {
        this.climberRepository = new ClimberRepository();
        this.mountainRepository = new MountainRepository();
    }

    @Override
    public String addClimber(String type, String climberName) {
        Climber climber;
        switch (type) {
            case "WallClimber":
                climber = new WallClimber(climberName);
                break;
            case "RockClimber":
                climber = new RockClimber(climberName);
                break;
            default:
                throw new IllegalArgumentException(CLIMBER_INVALID_TYPE);
        }
        climberRepository.add(climber);
        return String.format(CLIMBER_ADDED, type, climberName);
    }

    @Override
    public String addMountain(String mountainName, String... peaks) {
        Mountain mountain = new MountainImpl(mountainName);
        for (String peak : peaks) {
           mountain.getPeaksList().add(peak);
        }
        this.mountainRepository.add(mountain);
        return String.format(MOUNTAIN_ADDED, mountainName);
    }

    @Override
    public String removeClimber(String climberName) {
        Climber climber = climberRepository.byName(climberName);
        if (climber == null) {
            throw new IllegalArgumentException(String.format(CLIMBER_DOES_NOT_EXIST, climberName));
        }
        climberRepository.remove(climber);
        return String.format(CLIMBER_REMOVE, climberName);
    }

    @Override
    public String startClimbing(String mountainName) {
        List<Climber> climbers = this.climberRepository.getCollection().stream()
                .filter(c -> c.getStrength() > 0)
                .collect(Collectors.toList());
        if (climbers.isEmpty()) {
            throw new IllegalArgumentException(THERE_ARE_NO_CLIMBERS);
        }
        Mountain mountain = this.mountainRepository.byName(mountainName);
        Climbing climbing = new ClimbingImpl();
        climbing.conqueringPeaks(mountain, climbers);
        long excluded = climbers.stream().filter(c -> c.getStrength() == 0).count();
        this.count++;
        return String.format(PEAK_CLIMBING, mountainName, excluded);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(FINAL_MOUNTAIN_COUNT, count));
        sb.append(System.lineSeparator());
        sb.append(FINAL_CLIMBERS_STATISTICS);

        Collection<Climber> climbers = climberRepository.getCollection();

        for (Climber climber : climbers) {
            sb.append(System.lineSeparator());
            sb.append(String.format(FINAL_CLIMBER_NAME, climber.getName()));
            sb.append(System.lineSeparator());
            sb.append(String.format(FINAL_CLIMBER_STRENGTH, climber.getStrength()));
            sb.append(System.lineSeparator());
            if (climber.getRoster().getPeaks().isEmpty()) {
                sb.append(String.format(FINAL_CLIMBER_PEAKS, "None"));
            } else {
                sb.append(String.format(FINAL_CLIMBER_PEAKS,
                        String.join(FINAL_CLIMBER_FINDINGS_DELIMITER, climber.getRoster().getPeaks())));
            }
        }
        return sb.toString().trim();
    }
}
