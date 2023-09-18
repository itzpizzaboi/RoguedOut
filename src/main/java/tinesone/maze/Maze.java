package tinesone.maze;

import java.util.Arrays;

public class Maze {
    private final int width;
    private final int height;

    private final CellType maze[];

    public Maze(int width, int height) {
        this.width = width;
        this.height = height;
        maze = new CellType[width * height];
        Arrays.fill(maze, CellType.EMPTY);
    }

    private int toIndex(int x, int y) {
        return x + width * y;
    }

    public CellType getElement(int x, int y)
    {
        return maze[toIndex(x, y)];
    }


    public void setElement(int x, int y, CellType cellType)
    {
        maze[toIndex(x, y)] = cellType;
    }

    public void printElements()
    {
        for(int x = 0; x <width; x++){
            for(int y = 0; y <height; y++){
                System.out.printf(getElement(x, y).toString() + ", ");
            }
            System.out.println("\n");
        }
    }

    public boolean isWall(int x, int y) {
        return maze[toIndex(x, y)] == CellType.WALL;
    }

}
