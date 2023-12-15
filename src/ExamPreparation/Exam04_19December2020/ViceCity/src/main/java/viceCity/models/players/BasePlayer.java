package viceCity.models.players;

import viceCity.models.guns.Gun;
import viceCity.repositories.interfaces.GunRepository;
import viceCity.repositories.interfaces.Repository;

import static viceCity.common.ExceptionMessages.*;


public abstract class BasePlayer implements Player {
    private String name;
    private int lifePoints; //the health of the player
    private Repository<Gun> gunRepository; //generic repository of all player's guns

    protected BasePlayer(String name, int lifePoints) {
        setName(name);
        setLifePoints(lifePoints);
        this.gunRepository = new GunRepository();
    }
    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(PLAYER_NULL_USERNAME);
        }
        this.name = name;
    }

    private void setLifePoints(int lifePoints) {
        if (lifePoints < 0) {
            throw new NullPointerException(PLAYER_LIFE_POINTS_LESS_THAN_ZERO);
        }
        this.lifePoints = lifePoints;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public int getLifePoints() {
        return lifePoints;
    }
    @Override
    public Repository<Gun> getGunRepository() {
        return this.gunRepository;
    }
    @Override
    public boolean isAlive() {
        return this.lifePoints > 0;
    }
    @Override
    public void takeLifePoints(int points) {
        this.lifePoints -= points;
    }

}
