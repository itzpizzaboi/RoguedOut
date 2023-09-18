package tinesone.maze;

import static tinesone.maze.CellType.PATH;
import static tinesone.maze.CellType.WALL;

public class RandomPrim {
    public Maze generateMaze(int width, int height) {
        var maze = new Maze(width, height);
        generateMaze(maze);
        return maze;
    }

    private void generateMaze(Maze maze) {
        maze.setElement(0, 0, PATH);
        maze.setElement(1, 0, WALL);
        maze.setElement(0, 1, WALL);
    }
}
