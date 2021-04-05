package io.github.nosequel.scoreboard.thread;

import io.github.nosequel.scoreboard.ScoreboardHandler;
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
            this.handler.getAdapter().handleElement(player, this.handler.getHandler().getElement(player));
        }
    }
}