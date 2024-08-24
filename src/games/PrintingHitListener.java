// 211604343 Shaked Gitta

package games;

import collidables.Block;
import sprites.Ball;
/**
 * The PrintingHitListener class is responsible for printing a message when a block is hit.
 */
public class PrintingHitListener  {
    /**
     * This method is called when a block is hit.
     *
     * @param beingHit the block that was hit.
     * @param hitter   the ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block was hit.");
    }
}
