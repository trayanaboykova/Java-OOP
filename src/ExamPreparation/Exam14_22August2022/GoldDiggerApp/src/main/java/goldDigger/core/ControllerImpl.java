package goldDigger.core;

import goldDigger.models.discoverer.Anthropologist;
import goldDigger.models.discoverer.Archaeologist;
import goldDigger.models.discoverer.Discoverer;
import goldDigger.models.discoverer.Geologist;
import goldDigger.models.operation.Operation;
import goldDigger.models.operation.OperationImpl;
import goldDigger.models.spot.Spot;
import goldDigger.models.spot.SpotImpl;
import goldDigger.repositories.DiscovererRepository;
import goldDigger.repositories.SpotRepository;

import java.util.List;
import java.util.stream.Collectors;

import static goldDigger.common.ConstantMessages.*;
import static goldDigger.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private DiscovererRepository discovererRepository;
    private SpotRepository spotRepository;
    private int discoveredSpotsCount;

    public ControllerImpl() {
        this.discovererRepository = new DiscovererRepository();
        this.spotRepository = new SpotRepository();
        this.discoveredSpotsCount = 0;
    }

    @Override
    public String addDiscoverer(String kind, String discovererName) {
        Discoverer discoverer;

        switch (kind) {
            case "Archaeologist":
                discoverer = new Archaeologist(discovererName);
                break;
            case "Geologist":
                discoverer = new Geologist(discovererName);
                break;
            case "Anthropologist":
                discoverer = new Anthropologist(discovererName);
                break;
            default:
                throw new IllegalArgumentException(DISCOVERER_INVALID_KIND);
        }

        this.discovererRepository.add(discoverer);

        return String.format(DISCOVERER_ADDED, kind, discovererName);
    }

    @Override
    public String addSpot(String spotName, String... exhibits) {
        Spot spot = new SpotImpl(spotName);

        spot.getExhibits().addAll(List.of(exhibits));

        this.spotRepository.add(spot);

        return String.format(SPOT_ADDED, spotName);
    }

    @Override
    public String excludeDiscoverer(String discovererName) {
        Discoverer discoverer = this.discovererRepository.byName(discovererName);

        if (discoverer == null) {
            throw new IllegalArgumentException(String.format(DISCOVERER_DOES_NOT_EXIST, discovererName));
        }

        this.discovererRepository.remove(discoverer);

        return String.format(DISCOVERER_EXCLUDE, discovererName);
    }

    @Override
    public String inspectSpot(String spotName) {
        List<Discoverer> suitableDiscoverers = this.discovererRepository.getCollection()
                .stream()
                .filter(e -> e.getEnergy() > 45)
                .collect(Collectors.toList());

        int initialDiscovererCount = suitableDiscoverers.size();

        if (suitableDiscoverers.isEmpty()) {
            throw new IllegalArgumentException(SPOT_DISCOVERERS_DOES_NOT_EXISTS);
        }

        Spot spot = this.spotRepository.byName(spotName);

        Operation operation = new OperationImpl();
        operation.startOperation(spot, suitableDiscoverers);

        int leftDicoverersCount = suitableDiscoverers
                .stream()
                .filter(e -> e.getEnergy() > 0)
                .collect(Collectors.toList())
                .size();

        this.discoveredSpotsCount++;

        return String.format(INSPECT_SPOT, spot.getName(), initialDiscovererCount - leftDicoverersCount);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(FINAL_SPOT_INSPECT, this.discoveredSpotsCount)).append(System.lineSeparator());
        sb.append(FINAL_DISCOVERER_INFO).append(System.lineSeparator());
        for (Discoverer discoverer : this.discovererRepository.getCollection()) {
            sb.append(formatDiscovererInfo(discoverer));
        }
        return sb.toString().trim();
    }
    private String formatDiscovererInfo(Discoverer discoverer) {
        String exhibits = "None";
        if (discoverer.getMuseum() != null && !discoverer.getMuseum().getExhibits().isEmpty()) {
            exhibits = discoverer.getMuseum().getExhibits()
                    .stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(FINAL_DISCOVERER_MUSEUM_EXHIBITS_DELIMITER));
        }
        return String.format(FINAL_DISCOVERER_NAME, discoverer.getName()) + System.lineSeparator()
                + String.format(FINAL_DISCOVERER_ENERGY, discoverer.getEnergy()) + System.lineSeparator()
                + String.format(FINAL_DISCOVERER_MUSEUM_EXHIBITS, exhibits) + System.lineSeparator();
    }
}

