package football.entities.player;

import static football.common.ExceptionMessages.*;

public abstract class BasePlayer implements Player {
    private String name;
    private String nationality;
    private double kg;
    private int strength;

    public BasePlayer(String name, String nationality, double kg, int strength) {
        setName(name);
        setNationality(nationality);
        this.kg = kg;
        setStrength(strength);
    }

    public String getNationality() {
        return nationality;
    }

    private void setNationality(String nationality) {
        if (nationality == null || nationality.isBlank()) {
            throw new NullPointerException(PLAYER_NATIONALITY_NULL_OR_EMPTY);
        }
        this.nationality = nationality;
    }

    public void setStrength(int strength) {
        if (strength <= 0) {
            throw new IllegalArgumentException(PLAYER_STRENGTH_BELOW_OR_EQUAL_ZERO);
        }
        this.strength = strength;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new NullPointerException(PLAYER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public void stimulation() {

    }

    @Override
    public double getKg() {
        return kg;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getStrength() {
        return strength;
    }
}
