package football.core;


import football.entities.field.ArtificialTurf;
import football.entities.field.Field;
import football.entities.field.NaturalGrass;
import football.entities.player.Men;
import football.entities.player.Player;
import football.entities.player.Women;
import football.entities.supplement.Liquid;
import football.entities.supplement.Powdered;
import football.entities.supplement.Supplement;
import football.repositories.SupplementRepositoryImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static football.common.ConstantMessages.*;
import static football.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private SupplementRepositoryImpl supplement;
    Collection<Field> fields;

    public ControllerImpl() {
        this.supplement = new SupplementRepositoryImpl();
        this.fields = new ArrayList<>();
    }

    @Override
    public String addField(String fieldType, String fieldName) {
        Field field;
        switch (fieldType) {
            case "ArtificialTurf":
                field = new ArtificialTurf(fieldName);
                break;
            case "NaturalGrass":
                field = new NaturalGrass(fieldName);
                break;
            default:
                throw new NullPointerException(INVALID_FIELD_TYPE);
        }
        fields.add(field);
        return String.format(SUCCESSFULLY_ADDED_FIELD_TYPE, fieldType);
    }

    @Override
    public String deliverySupplement(String type) {
        Supplement supplementRepository;
        switch (type) {
            case "Powdered":
                supplementRepository = new Powdered();
                break;
            case "Liquid":
                supplementRepository = new Liquid();
                break;
            default:
                throw new IllegalArgumentException(INVALID_SUPPLEMENT_TYPE);
        }
        supplement.add(supplementRepository);
        return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE, type);
    }

    @Override
    public String supplementForField(String fieldName, String supplementType) {
        Field field = fields.stream()
                .filter(f -> f.getName().equals(fieldName))
                .findFirst()
                .get();
        Supplement supplement1 = supplement.findByType(supplementType);
        if (supplement1 == null) {
            throw new IllegalArgumentException(String.format(NO_SUPPLEMENT_FOUND, supplementType));
        }
        field.addSupplement(supplement1);
        supplement.remove(supplement1);
        return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_IN_FIELD, supplementType, fieldName);
    }

    @Override
    public String addPlayer(String fieldName, String playerType, String playerName, String nationality, int strength) {
        Player player;
        switch (playerType) {
            case "Men":
                player = new Men(playerName, nationality, strength);
                break;
            case "Women":
                player = new Women(playerName, nationality, strength);
                break;
            default:
                throw new IllegalArgumentException(INVALID_PLAYER_TYPE);
        }
        String output;
        Field field = fields.stream()
                .filter(f -> f.getName().equals(fieldName))
                .findFirst()
                .get();

        String fieldType = field.getClass().getSimpleName();

        if (playerType.equals("Men") && fieldType.equals("NaturalGrass") ||
                playerType.equals("Women") && fieldType.equals("ArtificialTurf")) {
            field.addPlayer(player);
            output = String.format(SUCCESSFULLY_ADDED_PLAYER_IN_FIELD, playerType, fieldName);
        } else {
            output = FIELD_NOT_SUITABLE;
        }
        return output;
    }

    @Override
    public String dragPlayer(String fieldName) {
        Field field = fields.stream()
                .filter(f -> f.getName().equals(fieldName))
                .findFirst()
                .orElse(null);
        for (Player p : field.getPlayers()) {
            p.stimulation();
        }
        return String.format(PLAYER_DRAG, field.getPlayers().size());
    }

    @Override
    public String calculateStrength(String fieldName) {
        Field field = fields.stream()
                .filter(f -> f.getName().equals(fieldName))
                .findFirst()
                .get();
        int sum = 0;
        for (Player p : field.getPlayers()) {
            sum += p.getStrength();
        }
        return String.format(STRENGTH_FIELD, fieldName, sum);
    }

    @Override
    public String getStatistics() {
        return fields.stream()
                .map(Field::getInfo)
                .collect(Collectors.joining(System.lineSeparator())).trim();
    }
}
