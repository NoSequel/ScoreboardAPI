<img src="https://i.imgur.com/vqe3G8d.png" width="50%" align="right">
<h1 align="center">Scoreboard API</h1>
<p align="center">is an opensource scoreboard API made for the Spigot API.</p><br>

# Todo
- Clean up parts of the code

# Usage
The usage is very easy - here's an example:

### Registering the handler itself:
Replace "ScoreboardImpl" with the ScoreboardElementHandler you're using (this is where the scoreboard api gets the elements from.)

```java
@Override
public void onEnable() {
    new ScoreboardHandler(this, ScoreboardImpl(), 20L);
}
```

## Making a new ScoreboardElementHandler implementation

```java
import io.github.nosequel.scoreboard.element.ScoreboardElement;
import io.github.nosequel.scoreboard.element.ScoreboardElementHandler;

public class ScoreboardImpl implements ScoreboardElementHandler {

    /**
     * Get the scoreboard element of a player
     *
     * @param player the player
     * @return the element
     */
    @Override
    public ScoreboardElement getElement(Player player) {
        final ScoreboardElement element = new ScoreboardElement();
        
        element.setTitle("Example Title");
        element.add(player.getName());
        
        if(player.getLocation().getX() >= 50) {
            element.add("your X axis is higher than 50");
        }
        
        return element;
    }
}
```

# Selling & Using
You're free to use this product for anything, including selling and running on your own server. However, if you're going to sell a plugin using this, please leave credits and/or a link to the repository.
