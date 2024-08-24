// 211604343 Shaked Gitta

package games;

import collidables.Block;
import sprites.Ball;

/**
 * The HitListener interface represents an object that listens to hit events in the game.
 */
public interface HitListener {
    // This method is called whenever the beingHit object is hit.
    // The hitter parameter is the Ball that's doing the hitting.
    /**
     * This method is called whenever the `beingHit` object is hit by a `hitter` ball.
     *
     * @param beingHit the block that was hit.
     * @param hitter   the ball that hit the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
