package io.github.nosequel.scoreboard.element;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ScoreboardElement {

    private final List<String> lines = new ArrayList<>();
    private String title;

    /**
     * Add a new line to the element
     *
     * @param string the line
     */
    public void add(String string) {
        this.lines.add(string);
    }

    /**
     * Add a new line to the element on a certain spot in the list
     *
     * @param index  the index to place the line on
     * @param string the line
     */
    public void add(int index, String string) {
        if (index > 16) {
            return;
        }

        this.lines.add(index, string);
    }
}