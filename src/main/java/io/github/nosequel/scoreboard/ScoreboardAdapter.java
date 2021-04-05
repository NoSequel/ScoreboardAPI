package io.github.nosequel.scoreboard;

import io.github.nosequel.scoreboard.element.ScoreboardElement;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreboardAdapter {

    private final Map<Player, List<String>> identifiers = new HashMap<>();

    /**
     * Handle an element to send to a player
     *
     * @param player  the player to send it to
     * @param element the element to send
     */
    public void handleElement(Player player, ScoreboardElement element) {
        if (!this.identifiers.containsKey(player)) {
            this.identifiers.put(player, new ArrayList<>());
        }

        final List<String> lines = element.getLines();
        final List<String> identifiers = this.identifiers.get(player);

        final Scoreboard board = this.getScoreboard(player);
        final Objective objective = this.getObjective(board);

        if (lines.isEmpty()) {
            player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        } else {

            for (int index = 0; index < 16; index++) {
                final String identifier = ChatColor.values()[index].toString() + ChatColor.WHITE;

                if (lines.size() - 1 < index) {
                    if (objective.getScore(identifier) == null) {
                        break;
                    }

                    this.removeEntry(board, identifier);
                } else {
                    final String line = lines.get(index);

                    final Team team = this.getTeam(board, identifier);
                    final String[] splitText = this.splitText(line);

                    team.setPrefix(splitText[0]);
                    team.setSuffix(splitText[1]);

                    identifiers.add(identifier);
                    objective.getScore(identifier).setScore(-index);
                }
            }

            objective.setDisplayName(element.getTitle());
            player.setScoreboard(board);
        }
    }

    /**
     * Clear the element from a player's scoreboard
     *
     * @param scoreboard the scoreboard to clear the entry from
     * @param identifier the identifier to get the score by
     */
    public void removeEntry(Scoreboard scoreboard, String identifier) {
        scoreboard.resetScores(identifier);
    }

    /**
     * Clear the element from a player's scoreboard
     *
     * @param player     the player to clear the elements for
     * @param scoreboard the scoreboard to clear the element from
     */
    public void removeElement(Player player, Scoreboard scoreboard) {
        this.identifiers.get(player).forEach(scoreboard::resetScores);
    }

    /**
     * Get the scoreboard of a player
     *
     * @param player the player to get the scoreboard by
     * @return the scoreboard
     */
    public Scoreboard getScoreboard(Player player) {
        return player.getScoreboard() == null || player.getScoreboard().equals(Bukkit.getScoreboardManager().getMainScoreboard())
                ? Bukkit.getScoreboardManager().getNewScoreboard()
                : player.getScoreboard();
    }

    /**
     * Get the objective for the scoreboard
     *
     * @param scoreboard the scoreboard to register the objective to
     * @return the found objective or a newly registered objective
     */
    private Objective getObjective(Scoreboard scoreboard) {
        Objective objective = scoreboard.getObjective("boardHandler");

        if (objective == null) {
            objective = scoreboard.registerNewObjective("boardHandler", "dummy");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        }

        return objective;
    }

    /**
     * Get the team to add the elements to
     *
     * @param scoreboard the scoreboard to register the team to
     * @param identifier the identifier to identify the team with
     * @return the found team or a newly registered team
     */
    private Team getTeam(Scoreboard scoreboard, String identifier) {
        final Team team = scoreboard.getTeam(identifier) == null
                ? scoreboard.registerNewTeam(identifier)
                : scoreboard.getTeam(identifier);

        if (team.getEntries().isEmpty() || !team.getEntries().contains(identifier)) {
            team.addEntry(identifier);
        }

        return team;
    }

    /**
     * Split the text to display on the scoreboard
     *
     * @param text the text to split
     * @return the split text
     */
    private String[] splitText(String text) {
        if (text.length() < 17) {
            return new String[]{text, ""};
        } else {
            final String left = text.substring(0, 16);
            final String right = text.substring(16);

            if (left.endsWith("§")) {
                return new String[]{
                        left.substring(0, left.toCharArray().length - 1),
                        StringUtils.left(ChatColor.getLastColors(left) + "§" + right, 16)
                };
            } else {
                return new String[]{
                        left,
                        StringUtils.left(ChatColor.getLastColors(left) + right, 16)
                };
            }
        }
    }
}