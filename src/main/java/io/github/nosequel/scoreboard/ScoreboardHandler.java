package io.github.nosequel.scoreboard;

import io.github.nosequel.scoreboard.element.ScoreboardElementHandler;
import io.github.nosequel.scoreboard.thread.ScoreboardRunnable;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class ScoreboardHandler {

    private final ScoreboardElementHandler handler;
    private final ScoreboardAdapter adapter;

    /**
     * Constructor to make a new scoreboard handler
     *
     * @param plugin         the plugin to register it to
     * @param elementHandler the handler to get the elements from
     * @param delay          the delay in between scoreboard ticks
     */
    public ScoreboardHandler(JavaPlugin plugin, ScoreboardElementHandler elementHandler, long delay) {
        this.handler = elementHandler;
        this.adapter = new ScoreboardAdapter();

        new ScoreboardRunnable(this).runTaskTimer(plugin, 0L, delay);
    }

}
