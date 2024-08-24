package sprites;

import biuoop.DrawSurface;
import games.Counter;
import games.GameLevel;
import games.Levels.LevelInformation;
import geometry.Rectangle;

import java.awt.*;

public class ScoreIndicator implements Sprite {
    private Rectangle rect;
    private Counter count;
    private Color color;
    private LevelInformation levelInformation;

    public ScoreIndicator(Rectangle rect, Color color, Counter counter, LevelInformation levelInformation) {
        this.rect = rect;
        this.color = color;
        this.count = counter;
        this.levelInformation=levelInformation;
    }


    /**
     * Draws the sprite on the given DrawSurface.
     *
     * @param d the DrawSurface on which the sprite should be drawn
     */
    public void drawOn(DrawSurface d) {
        String write = "Score:";
        String level= "Level Name:";
        String lives= "Lives:7";
        level= level+ levelInformation.levelName();
        write = write + Integer.toString(count.getValue());
        d.setColor(color);
        d.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
        d.setColor(Color.black);
        d.drawText((int) this.rect.getUpperLeft().getX() + 100, (int) this.rect.getUpperLeft().getY()+15, lives, 15);
        d.drawText((int) this.rect.getUpperLeft().getX() + 300, (int) this.rect.getUpperLeft().getY()+15, write, 15);
        d.drawText((int) this.rect.getUpperLeft().getX() + 500, (int) this.rect.getUpperLeft().getY()+15, level, 15);


    }

    /**
     * Notifies the sprite that a unit of time has passed.
     * This method is called once in each game tick.
     */

    // notify the sprite that time has passed
    public void timePassed() {
    }

    /**
     * Adds the sprite to the given game, so that it can be drawn and updated in the game.
     *
     * @param g the games.Game object to which the sprite should be added
     */

    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
