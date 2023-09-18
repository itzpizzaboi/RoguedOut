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
        maze.setElement(0, 0, PATH);
        maze.getAdjacentIndexList(0, 0).forEach((cellIndex) -> {
            maze.setElement(cellIndex, WALL);
        });
        Integer randomWallIndex = pickRandomWallIndex(maze);
        while (randomWallIndex != null) { // MAIN LOOP
            generatePaths(maze);
        }
    }

    private Integer pickRandomWallIndex(Maze maze) {
        if (maze.getWalls().size() == 0) {
            return null;
        }
        int randomWallIndex = (int) (Math.random() * maze.getWalls().size());
        return randomWallIndex;
    }

    private void generatePaths(Maze maze){
        int randomWallIndex = pickRandomWallIndex(maze);
        ArrayList<Integer> adjacentToWallIndexList = maze.getAdjacentIndexList(randomWallIndex);
        int pathCount = 0;
        for (int index : adjacentToWallIndexList) { // Count the amount of paths next to a random wall!
            if (maze.getElement(index) != PATH) {
                continue;
            }
            pathCount += 1;
        }
        if (pathCount == 1){
            maze.setElement(randomWallIndex, PATH);
        }
        else{
            maze.setElement(randomWallIndex, VISITED_WALL);
        }
    }
}