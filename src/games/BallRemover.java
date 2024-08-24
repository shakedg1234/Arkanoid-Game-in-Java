// 211604343 Shaked Gitta

package games;

import collidables.Block;
import sprites.Ball;

/**
 * BallRemover is responsible for removing balls from the game and keeping track of the remaining balls.
 * It implements the HitListener interface.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;
    /**
     * Constructs a new BallRemover with the specified game and remaining balls counter.
     *
     * @param game           the game from which the balls will be removed
     * @param removedBalls   the counter for the remaining balls
     */
    public BallRemover(GameLevel game, Counter removedBalls) {

        this.game = game;
        this.remainingBalls = removedBalls;


    }
    /**
     * Removes a ball from the game when it hits a block.
     * Decreases the remaining balls counter by 1.
     *
     * @param beingHit  the block being hit
     * @param hitter    the ball that hit the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        remainingBalls.decrease(1);
    }
}