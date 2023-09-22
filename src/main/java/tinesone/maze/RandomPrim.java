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
        maze.getAdjacentIndexList(2, 2).forEach((cellIndex) -> {
            maze.setElement(cellIndex, WALL);
        });
        Integer randomWallIndex = pickRandomWallIndex(maze);
        while (randomWallIndex != null) { // MAIN LOOP
            generatePaths(maze, randomWallIndex);
            randomWallIndex = pickRandomWallIndex(maze);
        }
        findCellTypeIndexList(maze, UNVISITED).forEach((cellIndex) -> { // Clean up any unvisited spaces
            cleanUpUnvisitedCell(maze, cellIndex);
        });
    }

    private Integer pickRandomWallIndex(Maze maze) {
        if (findCellTypeIndexList(maze, WALL).isEmpty()) {
            return null;
        }
        ArrayList<Integer> wallList = findCellTypeIndexList(maze, WALL);
        return wallList.get((int) Math.floor(Math.random() * wallList.size()));
    }

    private ArrayList<Integer> findCellTypeIndexList(Maze maze, CellType cellType)
    {
        ArrayList<Integer> indexList = new ArrayList<>();
        for (int index = 0; index < maze.getSize(); index++){
            if (maze.getElement(index) != cellType){
                continue;
            }
            indexList.add(index);
        }
        return indexList;
    }

    private void cleanUpUnvisitedCell(Maze maze, int cellIndex){
        ArrayList<Integer> adjacentCells = maze.getAdjacentIndexList(cellIndex);
        int adjacentPathCount = 0;
        for(int adjacentCellIndex : adjacentCells){
            CellType cellType = maze.getElement(adjacentCellIndex);
            if (cellType != PATH) { continue;}
            adjacentPathCount += 1;
        }
        if (adjacentPathCount >= 1){
            maze.setElement(cellIndex, PATH);
        }
        else{
            maze.setElement(cellIndex, VISITED_WALL);
        }
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