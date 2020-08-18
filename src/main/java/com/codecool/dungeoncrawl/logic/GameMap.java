package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;

import java.util.ArrayList;


public class GameMap {

    String[] maps = {"/map.txt", "/map2.txt", "/map3.txt"};

    private final int width;
    private final int height;
    private final Cell[][] cells;

    private Player player;
    private Cell door;

    private final ArrayList<Skeleton> skeletons;


    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
        skeletons = new ArrayList<>();
    }


    // Todo: Refactor this thingy here
    public GameMap getMapByLevel(int level) {
        return MapLoader.loadMap(maps[level]);
    }

    public void checkSkeletons() {
        skeletons.removeIf(skeleton -> skeleton.getHealth() <= 0);
    }


    public void addSkeleton(Skeleton skeleton) {
        skeletons.add(skeleton);
    }


    public ArrayList<Skeleton> getSkeletons() {
        return skeletons;
    }


    public Cell getCell(int x, int y) {
        return cells[x][y];
    }


    public void setPlayer(Player player) {
        this.player = player;
    }


    public Player getPlayer() {
        return player;
    }


    public int getWidth() {
        return width;
    }


    public int getHeight() {
        return height;
    }


    public Cell getDoor() {
        return door;
    }


    public void setDoor(Cell door) {
        this.door = door;
    }
}
