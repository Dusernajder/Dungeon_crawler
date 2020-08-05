package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.dungeonitems.Key;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {

    // load map
    GameMap map = MapLoader.loadMap("/map.txt");
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();

    // labels
    Label healthLabel = new Label();
    Label inventoryLabel = new Label();


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
        // create grid
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        // labels
        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);
        ui.add(new Label(("\nInventory:")), 0, 1);
        ui.add(inventoryLabel, 0, 2);

        //create border pane
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        // create scene
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        scene.setOnKeyPressed(map.getPlayer()::onKeyPressed);
        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();

        // start game loop
        gameLoop();
    }


    private void gameLoop() {
        new AnimationTimer() {
            final float[] timeSum = {0};
            final static float FPS = 5;
            final float timePerTick = 1000000000 / FPS;
            float delta = 0;
            long now;
            long lastTime = System.nanoTime();
            long timer = 0;
            int ticks = 0;


            @Override
            public void handle(long l) {
                now = System.nanoTime();
                delta += (now - lastTime) / timePerTick;
                timer += now - lastTime;
                lastTime = now;

                if (delta >= 1) {
                    update();
                    ticks++;
                    delta--;
                }
                if (timer >= 1000000000) {
                    System.out.println("Ticks per Frame: " + ticks); // Prints Fps
                    ticks = 0;
                    timer = 0;
                }
                render();
            }
        }.start();
    }


    private void update() {
        // move skeletons
        map.getSkeletons().forEach(Skeleton::move);
        // check if key has been collected
        map.getPlayer().getInventory().forEach(dungeonItem -> {
            if (dungeonItem instanceof Key)
                map.getDoor().setType(CellType.OPENDOOR);
        });
        // UI
        healthLabel.setText("" + map.getPlayer().getHealth());
        inventoryLabel.setText(map.getPlayer().getStringInventory());
    }


    private void render() {
        // clear canvas
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        //draw map
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                }
                else if (cell.getDungeonItem() != null) {
                    Tiles.drawTile(context, cell.getDungeonItem(), x, y);
                }
                else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
            // pick up items from floor
            map.getPlayer().pickUp();
        }
    }
}
