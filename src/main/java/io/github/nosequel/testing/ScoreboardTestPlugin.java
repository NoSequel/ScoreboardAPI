package io.github.nosequel.testing;

import io.github.nosequel.scoreboard.ScoreboardHandler;
import io.github.nosequel.scoreboard.element.ScoreboardElement;
import org.bukkit.plugin.java.JavaPlugin;

public class ScoreboardTestPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        new ScoreboardHandler(this, player -> {
            final ScoreboardElement element = new ScoreboardElement();

            element.setTitle("ok men");
            element.add(player.getName());
            element.add(player.getLocation().getX() + ", " + player.getLocation().getZ());

            if(player.getLocation().getX() < 50) {
                element.add("hello ur X axis is less than 50");
            }

            return element;
        }, 5L);
    }

}
