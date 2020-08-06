//package com.codecool.dungeoncrawl.logic.actors.pathfind;
//
//import java.io.InputStream;
//import java.util.Scanner;
//
//
//public class PathFinding {
//
//
//    public int[][] readMap() {
//        InputStream is = PathFinding.class.getResourceAsStream("/map.txt");
//        Scanner scanner = new Scanner(is);
//
//        int width = scanner.nextInt();
//        int height = scanner.nextInt();
//
//        scanner.nextLine(); // empty line
//
//        int[][] map = new int[height][width];
//        for (int i = 0; i < height; i++) {
//            String line = scanner.nextLine();
//            for (int j = 0; j < width; j++) {
//                if (j < line.length())
//                    map[i][j] = line.charAt(j) == '#' ? 1 : 0;
//            }
//        }
//        return map;
//    }
//}
