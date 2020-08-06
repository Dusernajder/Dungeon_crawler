package com.codecool.dungeoncrawl.logic.dungeonitems;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.actors.Actor;

public class Pistol extends Weapon {

    public Pistol(Cell cell) {
        super(cell);
    }

    @Override
    public void weaponAttack(int dx, int dy, Cell cell) {
        int attackPower = 3;
        while (cell.getNeighbor(dx, dy).getActor() == null
                && cell.getNeighbor(dx, dy).getType() != CellType.WALL) {
            cell = cell.getNeighbor(dx, dy);
            System.out.println(cell.getX() + ", " + cell.getY() + ", " + cell.getType());
        }
        Actor actor = cell.getNeighbor(dx, dy).getActor();
        if (actor != null)
            actor.damage(attackPower);
    }

    @Override
    public String getTileName() {
        return "pistol";
    }
}
