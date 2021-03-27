package io.github.nosequel.testing;

import io.github.nosequel.scoreboard.ScoreboardHandler;
import io.github.nosequel.scoreboard.element.ScoreboardElement;
import io.github.nosequel.scoreboard.element.ScoreboardElementHandler;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ScoreboardTesting extends JavaPlugin {

    @Override
    public void onEnable() {
        new ScoreboardHandler(this, new ScoreboardElementHandler() {
            @Override
            public ScoreboardElement getElement(Player player) {
                final ScoreboardElement element = new ScoreboardElement();

                element.setTitle("Helo People");
                element.add("hi hi");

                if(player.getLocation().getX() >= 50) {
                    element.add("hey ur over 50 wtf !!");
                }

                return element;
            }
        }, 5L);
    }

}
