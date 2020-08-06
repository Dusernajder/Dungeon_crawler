package com.codecool.dungeoncrawl.logic.dungeonitems;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Actor;


public class Sword extends Weapon {

    public Sword(Cell cell) {
        super(cell);
    }

    @Override
    public void weaponAttack(int dx, int dy, Cell cell) {
        Cell neighbourCell = cell.getNeighbor(dx, dy);
        Actor actor = neighbourCell.getActor();
        int attackPower = 2;
        if (actor != null)
            actor.damage(attackPower);
    }

    @Override
    public String getTileName() {
        return "sword";
    }
}
