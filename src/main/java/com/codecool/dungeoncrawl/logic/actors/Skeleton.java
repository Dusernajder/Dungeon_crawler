package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.Util;
import com.codecool.dungeoncrawl.logic.Cell;


public class Skeleton extends Actor {

    public Skeleton(Cell cell) {
        super(cell);
        health = 5;
        attack = 2;
    }


    @Override
    public String getTileName() {
        return "skeleton";
    }


    public void move() {
        int dx = Util.RANDOM.nextInt(3) - 1;
        int dy = Util.RANDOM.nextInt(3) - 1;
        super.move(dx, dy);
    }
}