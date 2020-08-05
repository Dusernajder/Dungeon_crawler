package com.codecool.dungeoncrawl.logic.dungeonitems;

import com.codecool.dungeoncrawl.logic.Cell;


public class Key extends DungeonItem {

    public Key(Cell cell) {
        super(cell);
    }


    @Override
    public String getTileName() {
        return "key";
    }
}
