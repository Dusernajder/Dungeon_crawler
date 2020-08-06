package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    DOOR("door"),
    OPENDOOR("open door"),
    GATE("gate"),
    WATER("water");


    private final String tileName;


    CellType(String tileName) {
        this.tileName = tileName;
    }


    public String getTileName() {
        return tileName;
    }
}
