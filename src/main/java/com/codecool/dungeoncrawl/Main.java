package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
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
        final float[] timeSum = {0};
        new AnimationTimer() {
            @Override
            public void handle(long l) {
                timeSum[0] = timeSum[0] + l;
                if (timeSum[0] / 1000000000 > 1000) {
                    render();
                    update();
                    timeSum[0] = 0;
                }
                // TODO: different time intervals for player movement
            }
        }.start();
    }


    private void update() {
//        map.getSkeletons().forEach(Skeleton::move);

        if (map.getPlayer().getCell().getDungeonItem() != null
                && map.getPlayer().getCell().getDungeonItem().getTileName().equals("key")) {
            map.getDoor().setType(CellType.OPENDOOR);
            map.getPlayer().getCell().setDungeonItem(null);
        } else {
            map.getPlayer().pickUp();
        }
    }


    private void render() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else if (cell.getDungeonItem() != null) {
                    Tiles.drawTile(context, cell.getDungeonItem(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        healthLabel.setText("" + map.getPlayer().getHealth());
        inventoryLabel.setText(map.getPlayer().getStringInventory());
    }
}
