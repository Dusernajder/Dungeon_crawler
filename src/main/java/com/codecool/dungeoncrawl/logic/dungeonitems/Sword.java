package com.codecool.dungeoncrawl.logic.dungeonitems;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Actor;


public class Sword extends Weapon {

    private final int attackPower;

    public Sword(Cell cell, int attackPower) {
        super(cell);
        this.attackPower = attackPower;
    }

    @Override
    public void weaponAttack(int dx, int dy, Cell cell) {
        Cell neighbourCell = cell.getNeighbor(dx, dy);
        Actor actor = neighbourCell.getActor();
        if (actor != null)
            actor.takeDamage(attackPower);
    }

    public int getAttackPower() {
        return attackPower;
    }

    @Override
    public String getTileName() {
        return "sword";
    }
}
