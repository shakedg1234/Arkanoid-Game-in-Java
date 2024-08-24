// 211604343 Shaked Gitta
package sprites;

import biuoop.DrawSurface;
import collidables.Collidable;

import java.util.ArrayList;

/**
 * The Sprites.SpriteCollection class represents the environment in which the game operates.
 * It contains a list of all the Sprites objects in the game.
 */

public class SpriteCollection {
    private ArrayList<Sprite> sprites;

    /**
     * Creates a new empty Sprites.SpriteCollection.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }

    public SpriteCollection(ArrayList<Sprite> sprites1) {
        this.sprites = sprites1;
    }

    /**
     * Adds the specified sprite to the collection.
     *
     * @param s the sprite to add to the collection.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * Notifies all sprites in the collection that time has passed, and they need to move one step.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < this.sprites.size(); i++) {
            this.sprites.get(i).timePassed();
        }
    }

    /**
     * Draws all sprites in the collection on the specified DrawSurface.
     *
     * @param d the DrawSurface on which to draw the sprites.
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < this.sprites.size(); i++) {
            this.sprites.get(i).drawOn(d);
        }
    }

    public ArrayList<Sprite> getSpriteCollection() {
        return this.sprites;
    }
    public int getSize(){
        return this.sprites.size();
    }
}