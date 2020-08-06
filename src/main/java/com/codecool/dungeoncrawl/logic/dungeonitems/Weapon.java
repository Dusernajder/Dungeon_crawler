package com.codecool.dungeoncrawl.logic.dungeonitems;

import com.codecool.dungeoncrawl.logic.Cell;

public abstract class Weapon extends DungeonItem{

    public Weapon(Cell cell) {
        super(cell);
    }

    public abstract void weaponAttack(int dx, int dy, Cell cell);
}
