package tinesone.maze;

import java.util.ArrayList;

import static tinesone.maze.CellType.*;

public class RandomPrim {

    public Maze generateMaze(int width, int height) {
        var maze = new Maze(width, height);
        generateMaze(maze);
        return maze;
    }

    private void generateMaze(Maze maze) {
        maze.setElement(2, 2, PATH);
        maze.getAdjacentIndexList(1, 1).forEach((cellIndex) -> {
            maze.setElement(cellIndex, WALL);
        });
        Integer randomWallIndex = pickRandomWallIndex(maze);
        while (randomWallIndex != null) { // MAIN LOOP
            generatePaths(maze, randomWallIndex);
            randomWallIndex = pickRandomWallIndex(maze);
        }
        maze.getEmpty().forEach((cellIndex) -> { // Clean up any unvisited spaces
            maze.setElement(cellIndex, PATH);
        });
    }

    private Integer pickRandomWallIndex(Maze maze) {
        if (maze.getWalls().size() == 0) {
            return null;
        }
        ArrayList<Integer> wallList = maze.getWalls();
        int randomWallIndex = wallList.get((int) Math.floor(Math.random() * wallList.size()));
        return randomWallIndex;
    }

    private Integer pickRandomEmptyIndex(Maze maze) {
        if (maze.getEmpty().size() == 0) {
            return null;
        }
        ArrayList<Integer> emptyList = maze.getEmpty();
        int emptyIndex = emptyList.get((int) Math.floor(Math.random() * emptyList.size()));
        return emptyIndex;
    }

    private void generatePaths(Maze maze, int randomWallIndex){
        ArrayList<Integer> adjacentToWallIndexList = maze.getAdjacentIndexList(randomWallIndex);
        int visitedCellsCount = 0;
        for (int index : adjacentToWallIndexList) { // Count the amount of visited spaces
            CellType cellType = maze.getElement(index);
            if (cellType == WALL || cellType == UNVISITED) {
                continue;
            }
            visitedCellsCount += 1;
        }
        if (visitedCellsCount == 1){
            maze.setElement(randomWallIndex, PATH);
            adjacentToWallIndexList.forEach((cellIndex) -> {
                if (maze.getElement(cellIndex) == UNVISITED) {
                    maze.setElement(cellIndex, WALL);
                }
            });
        }
        else{
            maze.setElement(randomWallIndex, VISITED_WALL);
        }
    }
}