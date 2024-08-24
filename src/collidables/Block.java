// 211604343 Shaked Gitta
package collidables;

import biuoop.DrawSurface;
import games.GameLevel;
import games.HitListener;
import games.HitNotifier;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import sprites.Ball;
import sprites.Sprite;
import sprites.Velocity;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

/**
 * This class represents a Block object which implements the Collidable and
 * Sprite interfaces.
 * Blocks can be drawn on a DrawSurface, added to a Game, and can collide with other objects.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private static final double EPSILON = 1E-10;
    private ArrayList<HitListener> hitListeners;
    private Rectangle rect;
    private Color color;
    /**
     * Constructs a new Block with the specified rectangle and color.
     *
     * @param rect  the rectangle representing the block's position and size
     * @param color the color of the block
     */

    public Block(Rectangle rect, Color color) {
        this.color = color;
        this.rect = rect;
        this.hitListeners = new ArrayList<HitListener>();

    }

    /**
     * Returns the collision rectangle of the block.
     *
     * @return the collision rectangle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * Returns the corners of the block as a list of points.
     *
     * @return the corners of the block as a list of points
     */
    public List<geometry.Point> getCorners() {
        List<geometry.Point> corners = new ArrayList<>();
        corners.add(this.rect.getUpperLeft());
        corners.add(new Point(rect.getUpperLeft().getX() + rect.getWidth(), rect.getUpperLeft().getY()));
        corners.add(new geometry.Point(rect.getUpperLeft().getX() + rect.getWidth(), rect.getUpperLeft().getY()
                + rect.getHeight()));
        corners.add(new geometry.Point(rect.getUpperLeft().getX(), rect.getUpperLeft().getY() + rect.getHeight()));
        return corners;
    }
    /**
     * Notifies the Block that it was hit by a collidable object at a given collision point with a given velocity.
     * The method returns the new velocity expected after the hit based on the force the object inflicted on the Block.
     *
     * @param hitter          the ball that hit the block
     * @param collisionPoint  the point where the collision occurred
     * @param currentVelocity the current velocity of the object that hit the block
     * @return the new velocity expected after the hit
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity v = currentVelocity;
        Line top = new Line(rect.getUpperLeft().getX(), rect.getUpperLeft().getY(), rect.getUpperLeft().getX()
                + rect.getWidth(), rect.getUpperLeft().getY());
        Line right = new Line(rect.getUpperLeft().getX() + rect.getWidth(), rect.getUpperLeft().getY(),
                rect.getUpperLeft().getX() + rect.getWidth(), rect.getUpperLeft().getY() + rect.getHeight());
        Line bottom = new Line(rect.getUpperLeft().getX(), rect.getUpperLeft().getY() + rect.getHeight(),
                rect.getUpperLeft().getX() + rect.getWidth(), rect.getUpperLeft().getY() + rect.getHeight());
        Line left = new Line(rect.getUpperLeft().getX(), rect.getUpperLeft().getY(), rect.getUpperLeft().getX(),
                rect.getUpperLeft().getY() + rect.getHeight());
        // Check if the collision occurred on one of the corners of the rectangle
        if (collisionPoint.equals(rect.getUpperLeft()) || collisionPoint.equals(rect.getBottomLeft())
                || collisionPoint.equals(rect.getBottomRight()) || collisionPoint.equals(rect.getUpperRight())) {

            v = new Velocity((-1) * currentVelocity.getX(), (-1) * currentVelocity.getY());
            notifyHit(hitter);
            return v;

        } else {
            // Check if the collision occurred on the top or bottom side of the rectangle
            if (top.intersectionWithPoint(collisionPoint)) {
                currentVelocity.setY((-1) * currentVelocity.getY());

            } else if (bottom.intersectionWithPoint(collisionPoint)) {
                currentVelocity.setY((-1) * currentVelocity.getY());
            }
            // Check if the collision occurred on the right side of the rectangle
            if (left.intersectionWithPoint(collisionPoint)) {
                currentVelocity.setX((-1) * currentVelocity.getX());


            } else if (right.intersectionWithPoint(collisionPoint)) {
                currentVelocity.setX((-1) * currentVelocity.getX());
            }
        }
        notifyHit(hitter);
        return currentVelocity;
    }

    /**
     * Draws the block on a given DrawSurface.
     *
     * @param d the DrawSurface to draw the block on
     */
    @Override
    public void drawOn(DrawSurface d) {

        d.setColor(color);
        d.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
    }

    /**
     * This method is called once per frame and updates the state of the block.
     */

    @Override
    public void timePassed() {

    }

    /**
     * Adds the block to a given Game object.
     *
     * @param g the games.Game to add the block to
     */

    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
    /**
     * Removes the block from the specified game.
     *
     * @param g the game to remove the block from
     */

    public void removeFromGame(GameLevel g) {
        g.removeCollidable(this);
        g.removeSprite(this);
    }

    /**
     * Notifies all the registered HitListener objects that the block was hit by a ball.
     *
     * @param hitter the ball that hit the block
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }

    }
    /**
     * Adds a HitListener to the list of listeners for hit events.
     *
     * @param hl the HitListener to add
     */

    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);

    }


    /**
     * Removes a HitListener from the list of listeners for hit events.
     *
     * @param hl the HitListener to remove
     */
    public void removeHitListener(HitListener hl) {
        if (hitListeners.contains(hl)) {
            hitListeners.remove(hl);
        }

    }
}
