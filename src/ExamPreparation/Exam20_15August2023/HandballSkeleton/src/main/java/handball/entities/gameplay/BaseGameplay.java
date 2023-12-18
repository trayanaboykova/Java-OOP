package handball.entities.gameplay;

import handball.entities.equipment.Equipment;
import handball.entities.team.Team;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static handball.common.ExceptionMessages.GAMEPLAY_NAME_NULL_OR_EMPTY;

public abstract class BaseGameplay implements Gameplay {
    private String name;
    private int capacity;
    private Collection<Equipment> equipments;
    private Collection<Team> teams;

    public BaseGameplay(String name, int capacity) {
        setName(name);
        this.capacity = capacity;
        this.equipments = new ArrayList<>();
        this.teams = new ArrayList<>();
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new NullPointerException(GAMEPLAY_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Collection<Equipment> getEquipments() {
        return equipments;
    }

    @Override
    public Collection<Team> getTeam() {
        return this.teams;
    }

    @Override
    public int allProtection() {
        return equipments.stream()
                .mapToInt(Equipment::getProtection)
                .sum();
    }

    @Override
    public void addTeam(Team team) {
        teams.add(team);
    }

    @Override
    public void removeTeam(Team team) {
        teams.remove(team);
    }

    @Override
    public void addEquipment(Equipment equipment) {
        equipments.add(equipment);
    }

    @Override
    public void teamsInGameplay() {
        teams.forEach(Team::play);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        String teamNames = this.teams.stream().map(Team::getName).collect(Collectors.joining(" "));

        sb.append(String.format("%s %s", getName(), getClass().getSimpleName()));
        sb.append(System.lineSeparator());
        sb.append("Team: ");
        if (teamNames.isEmpty()){
            sb.append("none");
        } else {
            sb.append(teamNames);
        }
        sb.append(System.lineSeparator());
        sb.append("Equipment: ").append(String.format("%s, ", equipments.size()));
        sb.append(String.format("Protection: %s", allProtection()));


        return sb.toString().trim();
    }
}