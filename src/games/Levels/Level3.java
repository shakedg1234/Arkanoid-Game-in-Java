// 211604343 Shaked Gitta

package games.Levels;

import collidables.Block;
import geometry.Point;
import sprites.BuildingBackground;
import sprites.Sprite;
import sprites.Velocity;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * The Level3 class implements the LevelInformation interface and represents the information and configuration
 * of Level 3 in the game.
 */
public class Level3 implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 2;
    }

    // The initial velocity of each ball
    // Note that initialBallVelocities().size() == numberOfBalls()

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> velocityArrayList = new ArrayList<Velocity>();
        Velocity v1 = new Velocity(5, -5);
        Velocity v2 = new Velocity(-5, -5);
        velocityArrayList.add(v1);
        velocityArrayList.add(v2);
        return velocityArrayList;
    }

    public int paddleSpeed() {
        return 12;
    }

    @Override

    public int paddleWidth() {
        return 100;
    }

    @Override

    // the level name will be displayed at the top of the screen.
    public String levelName() {
        return "Level 3";
    }

    @Override

    // Returns a sprite with the background of the level
    public Sprite getBackground() {
//        Color color = new Color(0x7EB686);
//        Rectangle rectangle = new Rectangle(new Point(0, 0), 830, 630);
//        Block background = new Block(rectangle, color);
        Sprite background = new BuildingBackground();
        return background;

    }

    // The Blocks that make up this level, each block contains
    // its size, color and location.

    @Override
    public List<Block> blocks() {
        ArrayList<Block> list = new ArrayList<Block>();
        int numBlocks = 10;
        int c = 0;
        Color[] colors = {Color.black, Color.BLUE, Color.CYAN, Color.green, Color.yellow, Color.orange, Color.red};
        // Create the blocks
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j < numBlocks - i; j++) {
                geometry.Rectangle rectangle = new geometry.Rectangle(new Point(800
                        - 10 - 50 * j, 100
                        + 20 * i), 50, 20);
                Block block = new Block(rectangle, colors[c]);
                list.add(block);

            }
            c++;

        }
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


