package com.codecool.dungeoncrawl.logic.dungeonitems;

import com.codecool.dungeoncrawl.logic.Cell;


public class Sword extends DungeonItem {

    public Sword(Cell cell) {
        super(cell);
    }


    @Override
    public String getTileName() {
        return "sword";
    }
}
