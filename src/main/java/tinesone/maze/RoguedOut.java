package tinesone.maze;

import org.bukkit.plugin.java.JavaPlugin;

public class RoguedOut extends JavaPlugin {

    @Override
    public void onEnable() {
        RandomPrim mazeGenerator = new RandomPrim();
        mazeGenerator.generateMaze(14, 14).printElements();
    }
}