package org.rpg_lab;


public interface Target {
    boolean isDead();

    int giveExperience();

    void takeAttack(int attackPoints);

}
