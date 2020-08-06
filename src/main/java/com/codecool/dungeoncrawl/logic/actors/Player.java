package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.dungeonitems.DungeonItem;
import com.codecool.dungeoncrawl.logic.dungeonitems.Weapon;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;


public class Player extends Actor {

    private Weapon weapon;
    private int directionX = 0;
    private int directionY = -1;

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
                setPosition(0, -1);
                break;
            case DOWN:
                setPosition(0, 1);
                break;
            case LEFT:
                setPosition(-1, 0);
                break;
            case RIGHT:
                setPosition(1, 0);
                break;
            case SPACE:
                if (weapon != null)
                    weapon.weaponAttack(directionX, directionY, getCell());
                break;
        }
    }


    public String getTileName() {
        return "player";
    }


    public ArrayList<DungeonItem> getInventory() {
        return inventory;
    }


    public void pickUp() {
        DungeonItem item = getCell().getDungeonItem();
        if (item != null) {
            if (item.getClass().getSuperclass().getSimpleName().equals("Weapon")) {
                if (weapon == null || ((Weapon) item).getAttackPower() > weapon.getAttackPower())
                    weapon = (Weapon) item;
            }
            else {
                inventory.add(item);
            }
            getCell().setDungeonItem(null);
        }
    }


    public String getStringInventory() {
        StringBuilder inventoryString = new StringBuilder();
        if (weapon != null)
            inventoryString.append("\n").append(weapon.getTileName());
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


    private void setPosition(int dx, int dy) {
        directionX = dx;
        directionY = dy;
        moveIfPossible(dx, dy);

    }
}

