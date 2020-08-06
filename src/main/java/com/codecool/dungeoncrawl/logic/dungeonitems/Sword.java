package com.codecool.dungeoncrawl.logic.dungeonitems;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Actor;


public class Sword extends Weapon {

    private int attackPower = 2;

    public Sword(Cell cell) {
        super(cell);
    }

    @Override
    public void weaponAttack(int dx, int dy, Cell cell) {
        Cell neighbourCell = cell.getNeighbor(dx, dy);
        Actor actor = neighbourCell.getActor();
        System.out.println(actor);
        if (actor != null){
            actor.setHealth(actor.getHealth() - attackPower);
            if (actor.getHealth() <= 0)
                neighbourCell.setActor(null);
        }
    }

    @Override
    public String getTileName() {
        return "sword";
    }
}
