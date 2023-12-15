package aquarium.core;

import aquarium.entities.aquariums.Aquarium;
import aquarium.entities.aquariums.FreshwaterAquarium;
import aquarium.entities.aquariums.SaltwaterAquarium;
import aquarium.entities.decorations.Decoration;
import aquarium.entities.decorations.Ornament;
import aquarium.entities.decorations.Plant;
import aquarium.entities.fish.Fish;
import aquarium.entities.fish.FreshwaterFish;
import aquarium.entities.fish.SaltwaterFish;
import aquarium.repositories.DecorationRepository;

import java.util.ArrayList;
import java.util.Collection;

import static aquarium.common.ConstantMessages.*;
import static aquarium.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private DecorationRepository decorations;
    private Collection<Aquarium> aquariums;

    public ControllerImpl() {
        this.decorations = new DecorationRepository();
        this.aquariums = new ArrayList<>();
    }

    @Override
    public String addAquarium(String aquariumType, String aquariumName) {
        switch (aquariumType) {
            case "FreshwaterAquarium":
                this.aquariums.add(new FreshwaterAquarium(aquariumName));
                break;
            case "SaltwaterAquarium":
                this.aquariums.add(new SaltwaterAquarium(aquariumName));
                break;
            default:
                throw new NullPointerException(INVALID_AQUARIUM_TYPE);
        }

        return String.format(SUCCESSFULLY_ADDED_AQUARIUM_TYPE, aquariumType);
    }

    @Override
    public String addDecoration(String type) {
        switch (type) {
            case "Ornament":
                this.decorations.add(new Ornament());
                break;
            case "Plant":
                this.decorations.add(new Plant());
                break;
            default:
                throw new NullPointerException(INVALID_DECORATION_TYPE);
        }
        return String.format(SUCCESSFULLY_ADDED_DECORATION_TYPE, type);
    }

    @Override
    public String insertDecoration(String aquariumName, String decorationType) {
        Decoration decoration = this.decorations.findByType(decorationType);
        if (decoration == null) {
            throw new IllegalArgumentException(String.format(NO_DECORATION_FOUND, decorationType));
        }
        this.aquariums
                .stream()
                .filter(e -> e.getName().equals(aquariumName))
                .findFirst()
                .get()
                .addDecoration(decoration);

        this.decorations.remove(decoration);
        return String.format(SUCCESSFULLY_ADDED_DECORATION_IN_AQUARIUM, decorationType, aquariumName);
    }

    @Override
    public String addFish(String aquariumName, String fishType, String fishName, String fishSpecies, double price) {
        if (!fishType.equals("FreshwaterFish") && !fishType.equals("SaltwaterFish")) {
            throw new IllegalArgumentException(INVALID_FISH_TYPE);
        }

        Aquarium aquarium = this.aquariums.stream()
                .filter(e -> e.getName().equals(aquariumName))
                .findFirst()
                .get();

        switch (fishType) {
            case "FreshwaterFish":
                if (!aquarium.getClass().getSimpleName().contains("Freshwater")) {
                    return String.format(WATER_NOT_SUITABLE);
                }
                aquarium.addFish(new FreshwaterFish(fishName, fishSpecies, price));
                break;
            case "SaltwaterFish":
                if (!aquarium.getClass().getSimpleName().contains("Saltwater")) {
                    return String.format(WATER_NOT_SUITABLE);
                }
                aquarium.addFish(new SaltwaterFish(fishName, fishSpecies, price));
                break;
        }

        return String.format(SUCCESSFULLY_ADDED_FISH_IN_AQUARIUM, fishType, aquariumName);
    }

    @Override
    public String feedFish(String aquariumName) {
        Aquarium aquarium = this.aquariums
                .stream()
                .filter(e -> e.getName().equals(aquariumName))
                .findFirst()
                .get();

        aquarium.feed();

        int fedFish = aquarium.getFish().size();

        return String.format(FISH_FED, fedFish);
    }

    @Override
    public String calculateValue(String aquariumName) {
        double fishValue = this.aquariums
                .stream()
                .filter(e -> e.getName().equals(aquariumName))
                .findFirst()
                .get()
                .getFish()
                .stream()
                .mapToDouble(Fish::getPrice)
                .sum();

        double decorationsValue = this.aquariums
                .stream()
                .filter(e -> e.getName().equals(aquariumName))
                .findFirst()
                .get()
                .getDecorations()
                .stream()
                .mapToDouble(Decoration::getPrice)
                .sum();

        double totalValue = fishValue + decorationsValue;

        return String.format(VALUE_AQUARIUM, aquariumName, totalValue);
    }

    @Override
    public String report() {
        StringBuilder reportBuilder = new StringBuilder();

        for (Aquarium aquarium : aquariums) {
            reportBuilder.append(aquarium.getInfo()).append(System.lineSeparator());
        }

        return reportBuilder.toString().trim();
    }
}
