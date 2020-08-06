package com.codecool.dungeoncrawl.logic.dungeonitems;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.actors.Actor;

public class Pistol extends Weapon {

    private final int attackPower;

    public Pistol(Cell cell, int attackPower) {
        super(cell);
        this.attackPower = attackPower;
    }

    @Override
    public void weaponAttack(int dx, int dy, Cell cell) {
        while (cell.getNeighbor(dx, dy).getActor() == null
                && cell.getNeighbor(dx, dy).getType() != CellType.WALL) {
            cell = cell.getNeighbor(dx, dy);
        }
        Actor actor = cell.getNeighbor(dx, dy).getActor();
        if (actor != null)
            actor.damage(attackPower);
    }

    public int getAttackPower() {
        return attackPower;
    }

    @Override
    public String getTileName() {
        return "pistol";
    }
}
