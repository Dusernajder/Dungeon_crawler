package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;


public abstract class Actor implements Drawable {

    protected Cell cell;
    protected int level;
    protected int health;
    protected int attack;


    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }


    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;
    }


    public void moveIfPossible(int dx, int dy) {
        Cell neighbourCell = cell.getNeighbor(dx, dy);
        if (!(isNeighborWall(neighbourCell) ||
                isNeighborClosedDoor(neighbourCell) ||
                neighbourCell.getActor() != null)) {
            move(dx, dy);
        }
    }

    public void takeDamage(int attackPower) {
        health -= attackPower;
        if (health <= 0) {
            cell.setActor(null);
            cell.setType(CellType.BONE);
        }
    }


    private boolean isNeighborWall(Cell cell) {
        return cell.getTileName().equals("wall");
    }


    private boolean isNeighborClosedDoor(Cell cell) {
        return cell.getTileName().equals("door");
    }

    public int getLevel() {
        return level;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Cell getCell() {
        return cell;
    }


    public int getX() {
        return cell.getX();
    }


    public int getY() {
        return cell.getY();
    }
}
