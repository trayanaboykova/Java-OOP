package football.entities.field;

import football.entities.player.Player;
import football.entities.supplement.Supplement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import static football.common.ConstantMessages.*;
import static football.common.ExceptionMessages.*;

public abstract class BaseField implements Field {
    private String name;
    private int capacity;
    private Collection<Supplement> supplements;
    private Collection<Player> players;

    public BaseField(String name, int capacity) {
        setName(name);
        this.capacity = capacity;
        this.supplements = new ArrayList<>();
        this.players = new ArrayList<>();
    }

    private void setName(String name) {
            if (name == null || name.isBlank()) {
                throw new NullPointerException(FIELD_NAME_NULL_OR_EMPTY);
            }
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Collection<Supplement> getSupplements() {
        return Collections.unmodifiableCollection(supplements);
    }

    @Override
    public Collection<Player> getPlayers() {
        return Collections.unmodifiableCollection(players);
    }

    @Override
    public int sumEnergy() {
        return supplements
                .stream()
                .mapToInt(Supplement::getEnergy)
                .sum();
    }

    @Override
    public void addPlayer(Player player) {
        if (players.size() < capacity) {
            players.add(player);
        } else {
            throw new IllegalStateException(NOT_ENOUGH_CAPACITY);
        }
    }

    @Override
    public void removePlayer(Player player) {
        players.remove(player);
    }

    @Override
    public void addSupplement(Supplement supplement) {
        supplements.add(supplement);
    }

    @Override
    public void drag() {
        players.forEach(Player::stimulation);

    }

    @Override
    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s (%s):", name, getClass().getSimpleName()));
        sb.append(System.lineSeparator());
        sb.append("Player:");
        if (players.isEmpty()){
            sb.append(" none");
        } else {
            for (Player player:players) {
                sb.append(String.format(" %s", player.getName()));
            }
        }
        sb.append(System.lineSeparator());
        sb.append("Supplement: ").append(supplements.size());
        sb.append(System.lineSeparator());
        sb.append("Energy: ").append(sumEnergy());

        return sb.toString().trim();
    }
}
