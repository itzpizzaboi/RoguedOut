package tinesone.maze;

public class Main {
    public static void main(String[] args) {
        RandomPrim mazeGenerator = new RandomPrim();
        mazeGenerator.generateMaze(10, 10).printElements();
    }
}