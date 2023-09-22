package tinesone.maze;


import java.util.ArrayList;
import java.util.Arrays;

import static tinesone.maze.CellType.*;

public class Maze {
    private final int width;
    private final int height;


    private final CellType maze[];

    public Maze(int width, int height) {
        this.width = width + 2;
        this.height = height + 2;
        maze = new CellType[this.width * this.height];
        Arrays.fill(maze, UNVISITED);
        fillOuterWalls();
    }

    private void fillOuterWalls()
    {
        for (int x = 0; x <width; x++){
            setElement(x, 0, VISITED_WALL);
            setElement(x, height-1, VISITED_WALL);
        }
        for (int y = 0; y <height; y++){
            setElement(0, y, VISITED_WALL);
            setElement(width-1, y, VISITED_WALL);
        }
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
            String line = "";
            for(int y = 0; y <height; y++){
                switch(getElement(x, y)){
                    case PATH:
                        line += "o ";
                        break;
                    case VISITED_WALL:
                        line += "x ";
                        break;
                    case WALL:
                        line += String.format("x: %d, y: %d. UNRESOLVED WALL ", x, y);
                        break;
                    case UNVISITED:
                        line += String.format("x: %d, y: %d. UNVISITED SPACE ", x, y);
                }
            }
            System.out.println(line);
        }
    }

    public ArrayList<Integer> getAdjacentIndexList(int x, int y) {
        ArrayList<Integer> indexList = new ArrayList<>();
        if (x+1 < getWidth()){
            if (getElement(x+1, y) != VISITED_WALL) {
                indexList.add(toIndex(x + 1, y));
            }
        }
        if (x-1 >= 0){
            if (getElement(x-1, y) != VISITED_WALL) {
                indexList.add(toIndex(x - 1, y));
            }
        }
        if (y+1 < getHeight()){
            if (getElement(x, y+1) != VISITED_WALL) {
                indexList.add(toIndex(x, y + 1));
            }
        }
        if (y-1 >= 0){
            if (getElement(x, y-1) != VISITED_WALL) {
                indexList.add(toIndex(x, y - 1));
            }
        }
        return indexList;
    }

    public ArrayList<Integer> getAdjacentIndexList(int index){
        int x = index % height;
        int y = index / width;
        return getAdjacentIndexList(x, y);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getSize(){
        return width*height;
    }
}
