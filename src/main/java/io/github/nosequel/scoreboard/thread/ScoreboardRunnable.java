package io.github.nosequel.scoreboard.thread;

import io.github.nosequel.scoreboard.ScoreboardHandler;
import io.github.nosequel.scoreboard.element.ScoreboardElement;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

@RequiredArgsConstructor
public class ScoreboardRunnable extends BukkitRunnable {

    private final ScoreboardHandler handler;

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            final ScoreboardElement element = this.handler.getHandler().getElement(player);

            if (!element.getLines().isEmpty()) {
                this.handler.getAdapter().handleElement(player, this.handler.getAdapter().getScoreboard(player), element);
            }
        }
    }
}