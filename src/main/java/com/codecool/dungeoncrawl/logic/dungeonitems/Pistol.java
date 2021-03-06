package com.codecool.dungeoncrawl.logic.dungeonitems;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.Tiles;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Pistol extends Weapon {

    private final int attackPower;


    public Pistol(Cell cell, int attackPower) {
        super(cell);
        this.attackPower = attackPower;
    }

    @Override
    public void weaponAttack(int dx, int dy, Cell cell) {
        Cell playerCell = cell;
        int steps = 0;
        while (cell.getNeighbor(dx, dy).getActor() == null
                && cell.getNeighbor(dx, dy).getType() != CellType.WALL
                && cell.getNeighbor(dx, dy).getType() != (CellType.DOOR)
                && cell.getNeighbor(dx, dy).getType() != (CellType.OPENDOOR)) {
            cell = cell.getNeighbor(dx, dy);
            steps++;
        }
        Actor actor = cell.getNeighbor(dx, dy).getActor();
        shootAnimation(dx, dy, playerCell, steps, actor);
    }

    private void shootAnimation(int dx, int dy, Cell playerCell, int steps, Actor actor) {
        if (steps > 0) {
            double speed = 0.03 * steps;
            Circle circle = new Circle();
            circle.setFill(Color.ORANGERED);
            circle.setRadius(5);
            circle.setLayoutX(playerCell.getX() * Tiles.TILE_WIDTH + Tiles.TILE_WIDTH / 2);
            circle.setLayoutY(playerCell.getY() * Tiles.TILE_WIDTH + Tiles.TILE_WIDTH / 2);
            Main.getPane().getChildren().add(circle);

            TranslateTransition transition = new TranslateTransition();
            transition.setDuration(Duration.seconds(speed));
            transition.setToX(dx * (steps * Tiles.TILE_WIDTH + Tiles.TILE_WIDTH));
            transition.setToY(dy * (steps * Tiles.TILE_WIDTH + Tiles.TILE_WIDTH));
            transition.setNode(circle);
            transition.play();
            transition.setOnFinished(actionEvent -> {
                Main.getPane().getChildren().removeAll(circle);
                if (actor != null)
                    actor.takeDamage(attackPower);
            });
        } else if (actor != null) {
            actor.takeDamage(attackPower);
        }
    }

    public int getAttackPower() {
        return attackPower;
    }


    @Override
    public String getTileName() {
        return "pistol";
    }
}
