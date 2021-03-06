package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.dungeonitems.Key;
import com.codecool.dungeoncrawl.logic.dungeonitems.Pistol;
import com.codecool.dungeoncrawl.logic.dungeonitems.Sword;

import java.io.InputStream;
import java.util.Scanner;


// Maybe a Singleton could work?!
public class MapLoader {


    public static GameMap loadMap(String text) {
        return loadMap(text, null);
    }


    public static GameMap loadMap(String text, Player player) {
        InputStream is = MapLoader.class.getResourceAsStream(text);
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case '%':
                            cell.setType(CellType.WATER);
                            break;
                        case '!':
                            cell.setType(CellType.DOOR);
                            map.setDoor(cell);
                            break;
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            map.addSkeleton(new Skeleton(cell));
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            player = getPlayer(player, cell);
                            map.setPlayer(player);
                            break;
                        case 'k':
                            cell.setType(CellType.FLOOR);
                            new Key(cell);
                            break;
                        case 'w':
                            cell.setType(CellType.FLOOR);
                            new Sword(cell, 2);
                            break;
                        case 'p':
                            cell.setType(CellType.FLOOR);
                            new Pistol(cell, 3);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }


    // Todo: Refactor, there are already too much "getPlayer" method exists
    private static Player getPlayer(Player player, Cell cell) {
        if (player == null) {
            player = new Player(cell);
        } else {
            player.setCell(cell);
        }
        return player;
    }
}