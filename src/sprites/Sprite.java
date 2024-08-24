package sprites; // 211604343 Shaked Gitta

import biuoop.DrawSurface;
import games.GameLevel;

/**
 * The Sprite interface represents a graphical object that can be drawn on a DrawSurface and
 * can be updated as time passes.
 */
public interface Sprite {
    /**
     * Draws the sprite on the given DrawSurface.
     *
     * @param d the DrawSurface on which the sprite should be drawn
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that a unit of time has passed.
     * This method is called once in each game tick.
     */

    // notify the sprite that time has passed
    void timePassed();

    /**
     * Adds the sprite to the given game, so that it can be drawn and updated in the game.
     *
     * @param g the Game object to which the sprite should be added
     */

    void addToGame(GameLevel g);
}