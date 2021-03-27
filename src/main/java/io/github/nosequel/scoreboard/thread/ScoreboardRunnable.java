package io.github.nosequel.scoreboard.thread;

import io.github.nosequel.scoreboard.ScoreboardHandler;
import io.github.nosequel.scoreboard.element.ScoreboardElement;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;

@RequiredArgsConstructor
public class ScoreboardRunnable extends BukkitRunnable {

    private final ScoreboardHandler handler;

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            final ScoreboardElement element = this.handler.getHandler().getElement(player);

            if (!element.getLines().isEmpty()) {
                final Scoreboard scoreboard = player.getScoreboard() == null || player.getScoreboard().equals(Bukkit.getScoreboardManager().getMainScoreboard())
                        ? Bukkit.getScoreboardManager().getNewScoreboard()
                        : player.getScoreboard();

                this.handler.getAdapter().handleElement(player, scoreboard, element);
            }
        }
    }
}