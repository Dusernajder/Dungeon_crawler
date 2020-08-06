package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.dungeonitems.DungeonItem;
import javafx.scene.input.KeyEvent;

import java.io.FileInputStream;
import java.util.ArrayList;


public class Player extends Actor {

    private ArrayList<DungeonItem> inventory = new ArrayList<>();

    public Player(Cell cell) {
        super(cell);
        level = 1;
        health = 10;
        attack = 5;

    }

    public void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                moveIfPossible(0, -1);
                break;
            case DOWN:
                moveIfPossible(0, 1);
                break;
            case LEFT:
                moveIfPossible(-1, 0);
                break;
            case RIGHT:
                moveIfPossible(1, 0);
                break;
        }
    }


    public String getTileName() {
        return "player";
    }


    public ArrayList<DungeonItem> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<DungeonItem> inventory) {
        this.inventory = inventory;
    }

    public void pickUp() {
        DungeonItem item = getCell().getDungeonItem();
        if (item != null) {
            inventory.add(item);
            getCell().setDungeonItem(null);
        }
    }


    public String getStringInventory() {
        StringBuilder inventoryString = new StringBuilder();
        for (DungeonItem dungeonItem : inventory) {
            inventoryString.append("\n").append(dungeonItem.getTileName());
        }
        return inventoryString.toString();
    }

    public void levelUp() {
        level += 1;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }
}
