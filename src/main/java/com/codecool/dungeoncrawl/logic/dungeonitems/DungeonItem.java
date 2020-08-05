package com.codecool.dungeoncrawl.logic.dungeonitems;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;


public abstract class DungeonItem implements Drawable {

    private final Cell cell;


    public DungeonItem(Cell cell) {
        this.cell = cell;
        this.cell.setDungeonItem(this);
    }


    public Cell getCell() {
        return cell;
    }
}
