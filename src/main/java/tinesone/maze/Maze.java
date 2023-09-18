package tinesone.maze;


import java.util.ArrayList;
import java.util.Arrays;

import static tinesone.maze.CellType.WALL;

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

    public CellType getElement(int index) { return maze[index]; }
    public CellType getElement(int x, int y)
    {
        return maze[toIndex(x, y)];
    }

    public void setElement(int index, CellType cellType) {maze[index] = cellType;}
    public void setElement(int x, int y, CellType cellType)
    {
        maze[toIndex(x, y)] = cellType;
    }

    public void printElements(){
        for(int x = 0; x <width; x++){
            for(int y = 0; y <height; y++){
                System.out.printf(getElement(x, y).toString() + ", ");
            }
            System.out.println("\n");
        }
    }

    public ArrayList<Integer> getAdjacentIndexList(int x, int y) {
        ArrayList<Integer> indexList = new ArrayList<>();
        if (x+1 <= getWidth()){
            indexList.add(toIndex(x+1, y));
        }
        if (x-1 >= 0){
            indexList.add(toIndex(x-1, y));
        }
        if (y+1 <= getHeight()){
            indexList.add(toIndex(x, y+1));
        }
        if (y-1 >= 0){
            indexList.add(toIndex(x, y-1));
        }
        return indexList;
    }

    public ArrayList<Integer> getAdjacentIndexList(int index){
        int x = index % height;
        int y = index / width;
        return getAdjacentIndexList(x, y);
    }


    public ArrayList<Integer> getWalls(){
        ArrayList<Integer> wallsIndex = new ArrayList<>();
        for (int index = 0; index <= maze.length; index++){
            if (getElement(index) != WALL){
                continue;
            }
            wallsIndex.add(index);
        }
        return wallsIndex;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
