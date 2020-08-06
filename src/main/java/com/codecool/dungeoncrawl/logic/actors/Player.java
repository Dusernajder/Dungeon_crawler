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
//            System.out.println(item.getClass().getSuperclass().getSimpleName());
            if (item.getClass().getSuperclass().getSimpleName().equals("Weapon")) {
                weapon = (Weapon)item;
            } else {
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

    public DungeonItem getWeapon() {
        return weapon;
    }

    public void setWeapon(DungeonItem weapon) {
        this.weapon = (Weapon)weapon;
    }

    private void setPosition(int dx, int dy){
        directionX = dx;
        directionY = dy;
        moveIfPossible(dx, dy);
//        System.out.println(dx + ", " + dy);
    }
}
