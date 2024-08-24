// 211604343 Shaked Gitta

package games.Levels;

import collidables.Block;
import sprites.Sprite;
import sprites.Velocity;

import java.util.List;

/**
 * The LevelInformation interface represents the information and configuration of a level in the game.
 */
public interface LevelInformation {
    /**
     * Returns the number of balls in the level.
     *
     * @return The number of balls.
     */
    int numberOfBalls();

    /**
     * Returns the initial velocities of the balls.
     * The size of the list should be equal to the number of balls.
     *
     * @return The list of initial ball velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * Returns the speed of the paddle in pixels per frame.
     *
     * @return The paddle speed.
     */
    int paddleSpeed();

    /**
     * Returns the width of the paddle in pixels.
     *
     * @return The paddle width.
     */
    int paddleWidth();

    /**
     * Returns the name of the level.
     * The level name will be displayed at the top of the screen.
     *
     * @return The level name.
     */
    String levelName();

    /**
     * Returns the background sprite of the level.
     *
     * @return The background sprite.
     */
    Sprite getBackground();

    /**
     * Returns the blocks that make up this level.
     * Each block contains its size, color, and location.
     *
     * @return The list of blocks.
     */
    List<Block> blocks();

    /**
     * Returns the number of blocks that should be removed before the level is considered "cleared".
     *
     * @return The number of blocks to remove.
     */
    int numberOfBlocksToRemove();
}
