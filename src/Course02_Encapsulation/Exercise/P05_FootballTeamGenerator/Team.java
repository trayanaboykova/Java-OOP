package Course02_Encapsulation.Exercise.P05_FootballTeamGenerator;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private List<Player> players;

    public Team(String name) {
        setName(name);
        this.players = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("A name should not be empty.");
        }
        this.name = name;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(String playerName) {
        boolean isRemoved = players.removeIf(e -> e.getName().equals(playerName));
        if (!isRemoved) {
            throw new IllegalArgumentException(String.format("Player %s is not in %s team.", playerName, this.name));
        }
        players.removeIf(e -> e.getName().equals(playerName));
    }

    public double getRating() {
        return players.stream()
                .mapToDouble(Player::overallSkillLevel)
                .average()
                .orElse(0);
    }
}
