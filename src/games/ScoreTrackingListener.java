// 211604343 Shaked Gitta
package games;

import collidables.Block;
import sprites.Ball;
/**
 * The ScoreTrackingListener class is responsible for tracking the score in the game.
 * It implements the HitListener interface and updates the score when a block is hit.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    /**
     * Constructs a ScoreTrackingListener instance with the given score counter.
     *
     * @param scoreCounter The counter to track the score.
     */

    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
       currentScore.increase(5);
    }
}