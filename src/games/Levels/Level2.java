// 211604343 Shaked Gitta

package games.Levels;

import collidables.Block;
import geometry.Point;
import geometry.Rectangle;
import sprites.Sprite;
import sprites.SunBackground;
import sprites.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The Level2 class implements the LevelInformation interface and represents the information and configuration
 * of Level 2 in the game.
 */

public class Level2 implements LevelInformation {

    private int count;

    @Override

    public int numberOfBalls() {
        return 10;
    }

    // The initial velocity of each ball
    // Note that initialBallVelocities().size() == numberOfBalls()

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> velocityArrayList = new ArrayList<Velocity>();
        double angle = 360 / numberOfBalls();
        double speed = 5;
        for (int i = 0; i < numberOfBalls(); i++) {
            Velocity v1 = Velocity.fromAngleAndSpeed(angle * (i + 1), speed);
            velocityArrayList.add(v1);

        }
        return velocityArrayList;


    }

    @Override

    public int paddleSpeed() {
        return 12;
    }

    @Override

    public int paddleWidth() {
        return 300;
    }

    // the level name will be displayed at the top of the screen.

    @Override
    public String levelName() {
        return "Level 2";
    }

    // Returns a sprite with the background of the level

    @Override
    public Sprite getBackground() {
//        Rectangle rectangle = new Rectangle(new Point(0, 0), 830, 630);
//
//        Block background = new Block(rectangle, Color.white);
        Sprite background = new SunBackground();
        return background;

    }

    // The Blocks that make up this level, each block contains
    // its size, color and location.

    @Override
    public List<Block> blocks() {
        ArrayList<Block> list = new ArrayList<Block>();

        Color[] colors = {Color.black, Color.BLUE, Color.CYAN, Color.green, Color.yellow, Color.orange, Color.red,
                Color.red, Color.red, Color.orange, Color.yellow, Color.green, Color.CYAN, Color.BLUE, Color.black};
        for (int i = 0; i < 15; i++) {
            Rectangle rectangle = new Rectangle(new Point(10 + (i * 52), 200), 52, 20);
            Block block = new Block(rectangle, colors[i]);
            list.add(block);
            count++;

        }
        return list;

    }

    // Number of blocks that should be removed
    // before the level is considered to be "cleared".
    // This number should be <= blocks.size();

    @Override
    public int numberOfBlocksToRemove() {
        return count;
    }
}