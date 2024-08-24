package collidables;
// 211604343 Shaked Gitta

import geometry.Point;
import geometry.Rectangle;
import sprites.Ball;
import sprites.Velocity;

/**
 * This interface represents a collidable object.
 * A collidable object is an object that can collide with other objects in the game.
 */
public interface Collidable {

    // Return the "collision shape" of the object.

    /**
     * Returns the collision shape of the collidable object.
     *
     * @return the collision shape of the collidable object
     */
    Rectangle getCollisionRectangle();


    /**
     * Notifies the Block that it was hit by a collidable object at a given collision point with a given velocity.
     * The method returns the new velocity expected after the hit based on the force the object inflicted on the Block.
     *
     * @param hitter          the ball that hit the block
     * @param collisionPoint  the point where the collision occurred
     * @param currentVelocity the current velocity of the object that hit the block
     * @return the new velocity expected after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}