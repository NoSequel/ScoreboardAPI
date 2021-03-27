package io.github.nosequel.scoreboard.element;

import org.bukkit.entity.Player;

public interface ScoreboardElementHandler {

    /**
     * Get the scoreboard element of a player
     *
     * @param player the player
     * @return the element
     */
    ScoreboardElement getElement(Player player);

}
