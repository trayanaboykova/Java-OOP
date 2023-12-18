package handball.core;

import handball.entities.equipment.ElbowPad;
import handball.entities.equipment.Equipment;
import handball.entities.equipment.Kneepad;
import handball.entities.gameplay.Gameplay;
import handball.entities.gameplay.Indoor;
import handball.entities.gameplay.Outdoor;
import handball.entities.team.Bulgaria;
import handball.entities.team.Germany;
import handball.entities.team.Team;
import handball.repositories.EquipmentRepository;
import handball.repositories.Repository;

import java.util.ArrayList;
import java.util.Collection;

import static handball.common.ConstantMessages.*;
import static handball.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private Repository equipments;
    private Collection<Gameplay> gameplays;

    public ControllerImpl() {
        this.equipments = new EquipmentRepository();
        this.gameplays = new ArrayList<>();
    }

    @Override
    public String addGameplay(String gameplayType, String gameplayName) {
        Gameplay gameplay;
        switch (gameplayType) {
            case "Outdoor":
                gameplay = new Outdoor(gameplayName);
                break;
            case "Indoor":
                gameplay = new Indoor(gameplayName);
                break;
            default:
                throw new NullPointerException(INVALID_GAMEPLAY_TYPE);
        }

        gameplays.add(gameplay);

        return String.format(SUCCESSFULLY_ADDED_GAMEPLAY_TYPE, gameplayType);
    }

    @Override
    public String addEquipment(String equipmentType) {
        Equipment equipment;
        switch (equipmentType) {
            case "Kneepad":
                equipment = new Kneepad();
                break;
            case "ElbowPad":
                equipment = new ElbowPad();
                break;
            default:
                throw new IllegalArgumentException(INVALID_EQUIPMENT_TYPE);
        }

        equipments.add(equipment);
        return String.format(SUCCESSFULLY_ADDED_EQUIPMENT_TYPE, equipmentType);
    }

    @Override
    public String equipmentRequirement(String gameplayName, String equipmentType) {
        Equipment equipment = this.equipments.findByType(equipmentType);
        if (equipment == null) {
            throw new IllegalArgumentException(String.format(NO_EQUIPMENT_FOUND, equipmentType));
        }
        this.gameplays.stream()
                .filter(g -> g.getName().equals(gameplayName))
                .findFirst()
                .ifPresent(gameplay -> gameplay.addEquipment(equipment));

        equipments.remove(equipment);
        return String.format(SUCCESSFULLY_ADDED_EQUIPMENT_IN_GAMEPLAY, equipmentType, gameplayName);
    }

    @Override
    public String addTeam(String gameplayName, String teamType, String teamName, String country, int advantage) {
        Team team;
        if (teamType.equals("Bulgaria")) {
            team = new Bulgaria(teamName, country, advantage);
        } else if (teamType.equals("Germany")) {
            team = new Germany(teamName, country, advantage);
        } else {
            throw new IllegalArgumentException(INVALID_TEAM_TYPE);
        }
        Gameplay gameplay = this.gameplays.stream()
                .filter(g -> g.getName().equals(gameplayName))
                .findFirst()
                .orElse(null);

        boolean isSuitable = gameplay.getClass().getSimpleName().equals("Outdoor") && teamType.equals("Bulgaria") ||
                gameplay.getClass().getSimpleName().equals("Indoor") && teamType.equals("Germany");

        if (!isSuitable) {
            return GAMEPLAY_NOT_SUITABLE;
        }

        gameplay.addTeam(team);
        return String.format(SUCCESSFULLY_ADDED_TEAM_IN_GAMEPLAY, teamType, gameplayName);

    }

    @Override
    public String playInGameplay(String gameplayName) {

        Gameplay gameplay = this.gameplays.stream()
                .filter(g -> g.getName().equals(gameplayName))
                .findFirst()
                .orElse(null);

        if (gameplay != null) {
            gameplay.teamsInGameplay();
        }
        assert gameplay != null;
        return String.format(TEAMS_PLAYED, gameplay.getTeam().size());

    }

    @Override
    public String percentAdvantage(String gameplayName) {

        Gameplay gameplay = this.gameplays.stream()
                .filter(g -> g.getName().equals(gameplayName))
                .findFirst()
                .orElse(null);

        assert gameplay != null;
        int sum = gameplay.getTeam().stream().mapToInt(Team::getAdvantage).sum();
        return String.format(ADVANTAGE_GAMEPLAY, gameplayName, sum);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        this.gameplays.forEach(sb::append);
        return sb.toString().trim();
    }
}
