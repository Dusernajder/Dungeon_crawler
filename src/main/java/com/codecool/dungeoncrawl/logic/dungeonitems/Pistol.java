package com.codecool.dungeoncrawl.logic.dungeonitems;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.actors.Actor;

public class Pistol extends Weapon {

    private final int attackPower = 3;

    public Pistol(Cell cell) {
        super(cell);
    }

    @Override
    public void weaponAttack(int dx, int dy, Cell cell) {
        while (cell.getNeighbor(dx, dy).getActor() == null
                && cell.getNeighbor(dx, dy).getType() != CellType.WALL) {
            cell = cell.getNeighbor(dx, dy);
            System.out.println(cell.getX() + ", " + cell.getY() + ", " + cell.getType());
        }
        Actor actor = cell.getNeighbor(dx, dy).getActor();
        if (actor != null) {
            actor.setHealth(actor.getHealth() - attackPower);
            if (actor.getHealth() <= 0){
                actor.getCell().setActor(null);
            }
        }
    }

    @Override
    public String getTileName() {
        return "pistol";
    }
}
