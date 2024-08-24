// 211604343 Shaked Gitta
package games;

import collidables.Block;
import sprites.Ball;

/**
 * BlockRemover is responsible for removing blocks from the game and keeping track of the remaining blocks.
 * It implements the HitListener interface.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;
    /**
     * Constructs a new BlockRemover with the specified game and remaining blocks counter.
     *
     * @param game           the game from which the blocks will be removed
     * @param removedBlocks  the counter for the remaining blocks
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;

    }
    /**
     * Removes a block from the game when it is hit by a ball.
     * Removes the BlockRemover listener from the block being removed.
     *
     * @param beingHit  the block being hit
     * @param hitter    the ball that hit the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {// Make a copy of the hitListeners before iterating over them.
        this.remainingBlocks.decrease(1);
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(game);
    }
}
