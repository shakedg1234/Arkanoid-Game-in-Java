// 211604343 Shaked Gitta

package games.Levels;

import collidables.Block;
import geometry.Point;
import geometry.Rectangle;
import sprites.Sprite;
import sprites.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * The Level1 class implements the LevelInformation interface and represents the information and configuration
 * of Level 1 in the game.
 */

public class Level1 implements LevelInformation {
    @Override

    public int numberOfBalls() {
        return 1;
    }

    // The initial velocity of each ball
    // Note that initialBallVelocities().size() == numberOfBalls()
    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> velocityArrayList = new ArrayList<Velocity>();
        Velocity v1 = new Velocity(0, 3);
        velocityArrayList.add(v1);
        return velocityArrayList;


    }

    @Override

    public int paddleSpeed() {
        return 12;
    }

    @Override

    public int paddleWidth() {
        return 100;
    }


    // the level name will be displayed at the top of the screen.

    @Override
    public String levelName() {
        return "Level 1";
    }

    // Returns a sprite with the background of the level

    @Override
    public Sprite getBackground() {
        Rectangle rectangle = new Rectangle(new Point(0, 0), 830, 630);
        Sprite background = new Block(rectangle, Color.black);
        return background;

    }

    // The Blocks that make up this level, each block contains
    // its size, color and location.

    @Override
    public List<Block> blocks() {
        ArrayList<Block> list = new ArrayList<Block>();
        Rectangle rectangle = new Rectangle(new Point(390, 100), 20, 20);
        Block block = new Block(rectangle, Color.red);
        list.add(block);
        return list;

    }

    // Number of blocks that should be removed
    // before the level is considered to be "cleared".
    // This number should be <= blocks.size();

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }

}
