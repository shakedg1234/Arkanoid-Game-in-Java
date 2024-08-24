// 211604343 Shaked Gitta
package games;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import collidables.Block;
import collidables.Collidable;
import collidables.GameEnvironment;
import collidables.Paddle;
import games.Animations.CountdownAnimation;
import games.Animations.KeyPressStoppableAnimation;
import games.Animations.AnimationRunner;
import games.Animations.EndScreen;
import games.Animations.PauseScreen;
import games.Levels.LevelInformation;
import games.Animations.Animation;
import geometry.Rectangle;
import sprites.Ball;
import sprites.ScoreIndicator;
import sprites.Sprite;
import sprites.SpriteCollection;
import sprites.Velocity;
//import sprites.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * The games.Game class represents the game itself, it holds all the game's components and
 * is responsible for running the game animation loop.
 */
public class GameLevel implements Animation {
    private static final int GUI_SIZE_X = 800;
    private static final int GUI_SIZE_Y = 600;
    private static final int BALL_RADUIS = 5;
    private static final int BLOCK_NUM = 13;
    private static final int WIDTH_OF_BLOCK = 50;
    private static final int WIDTH_OF_PADDLE = 100;
    private static final int HEIGHT_OF_PADDLE = 20;
    private static final int HEIGHT_OF_BLOCK = 20;
    private static final int VELOCITY_START = 3;
    private static final int BALL_START_X = 425;
    private static final int BALL_START_Y = 565;
    private static final int BOUNDARIES_SIZE = 10;
    private static final int PADDLE_START_X = 360;
    private static final int PADDLE_START_Y = 570;
    private static final int MIDDLE_X = 400;

    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private int guiSizeX;
    private int guiSizeY;
    private Counter counter;
    private Counter ballsCounter;
    private Counter remainingBlocks;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation levelInformation;
    private ScoreIndicator scoreIndicator;


    /**
     * Constructs a new GameLevel object.
     *
     * @param guiSizeX         the width of the game's GUI.
     * @param guiSizeY         the height of the game's GUI.
     * @param levelInformation the level information for the game level.
     */

    public GameLevel(int guiSizeX, int guiSizeY, LevelInformation levelInformation) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.guiSizeX = guiSizeX;
        this.guiSizeY = guiSizeY;
        this.gui = new GUI("Arkanoid game", guiSizeX, guiSizeY);
        this.counter = new Counter();
        this.ballsCounter = new Counter();
        this.remainingBlocks = new Counter();
        this.runner = new AnimationRunner(gui, 60);
        this.keyboard = this.gui.getKeyboardSensor();
        this.levelInformation = levelInformation;

    }

    /**
     * Constructs a new GameLevel object.
     *
     * @param keyboard         The keyboard sensor to listen for key presses.
     * @param levelInformation the level information for the game level.
     * @param ar               The AnimationRunner that responsible for running animations on a GUI.
     * @param counter          The score of the player.
     */


    public GameLevel(LevelInformation levelInformation, KeyboardSensor keyboard, AnimationRunner ar, Counter counter) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.guiSizeX = GUI_SIZE_X;
        this.guiSizeY = GUI_SIZE_Y;
        this.gui = ar.getGui();
//        this.gui = new GUI("Arkanoid game", GUI_SIZE_X, GUI_SIZE_Y);
        this.counter = counter;
        this.ballsCounter = new Counter();
        this.remainingBlocks = new Counter();
        this.runner = ar;
        this.keyboard = keyboard;
        this.levelInformation = levelInformation;

    }

    @Override

    public boolean shouldStop() {
        return !this.running;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // the logic from the previous run method goes here.
        // the `return` or `break` statements should be replaced with
        // this.running = false;

        // game-specific logic
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    new PauseScreen(this.keyboard)));
//            this.runner.run(new PauseScreen(this.keyboard));
        }
        // stopping condition
        if (this.ballsCounter.getValue() == 0) {
            this.running = false;
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    new EndScreen(counter.getValue(), this.keyboard)));
//            this.runner.run(new EndScreen(counter.getValue(), this.keyboard));
            gui.close();
        }
        if (this.remainingBlocks.getValue() == 0) {
            this.running = false;
            this.counter.increase(100);
        }

    }

    /**
     * Returns the remaingBlocks in the level.
     *
     * @return int remainingBlock value.
     */

    public int getRemainingBlocks() {
        return remainingBlocks.getValue();
    }

    /**
     * Returns the remining Ball in the level.
     *
     * @return int remaining Ball value
     */

    public int getBallsCounter() {
        return ballsCounter.getValue();
    }

    /**
     * Adds a new collidable object to the game environment.
     *
     * @param c the collidable to add to the environment.
     */


    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);

    }

    /**
     * Adds a new sprite object to the game sprites collection.
     *
     * @param s the sprite to add to the collection.
     */

    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Returns the game environment.
     *
     * @return the game environment.
     */

    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * Returns the game sprites collection.
     *
     * @return the game sprites collection.
     */

    public SpriteCollection getSprites() {
        return this.sprites;
    }

    /**
     * Removes a collidable from the game environment.
     *
     * @param c the collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        ArrayList<Collidable> collidables = environment.getCollidables();
        if (collidables.contains(c)) {
            collidables.remove(c);
        }
        GameEnvironment newEnv = new GameEnvironment(collidables);
        this.environment = newEnv;
    }

    /**
     * Removes a sprite from the game sprites collection.
     *
     * @param s the sprite to remove.
     */
    public void removeSprite(Sprite s) {
        ArrayList<Sprite> spritesc = sprites.getSpriteCollection();
        if (spritesc.contains(s)) {
            spritesc.remove(s);
        }
        SpriteCollection newSprite = new SpriteCollection(spritesc);
        this.sprites = newSprite;
    }

    /**
     * Creates and adds the game's borders (left, top, right, bottom) to the game.
     * Also adds a BallRemover as a hit listener to the bottom border.
     *
     * @param ballRemover the BallRemover object to add as a hit listener to the bottom border.
     */
    public void borders(BallRemover ballRemover) {
        Rectangle borderleft = new geometry.Rectangle(new geometry.Point(0, 0), BOUNDARIES_SIZE, this.guiSizeY);
        geometry.Rectangle bordertop = new geometry.Rectangle(new geometry.Point(0, 10), this.guiSizeX, 10);
        geometry.Rectangle borderright = new geometry.Rectangle(new geometry.Point(this.guiSizeX
                - BOUNDARIES_SIZE, 0), BOUNDARIES_SIZE, guiSizeX);
        geometry.Rectangle borderbottom = new geometry.Rectangle(new geometry.Point(0, this.guiSizeY + 20
                - BOUNDARIES_SIZE), guiSizeX, BOUNDARIES_SIZE);
        Block borderl = new Block(borderleft, Color.GRAY);
        Block bordert = new Block(bordertop, Color.GRAY);
        Block borderr = new Block(borderright, Color.GRAY);
        Block boederb = new Block(borderbottom, Color.GRAY);
        // Add the borders of the game screen (left, top, right, bottom)
        borderl.addToGame(this);
        bordert.addToGame(this);
        borderr.addToGame(this);
        boederb.addToGame(this);
        boederb.addHitListener(ballRemover);

    }


    /**
     * Initializes the game by creating and adding the game's blocks, ball(s) and paddle.
     */

    public void initialize() {
        Sprite background = this.levelInformation.getBackground();
        this.sprites.addSprite(background);
        BlockRemover blockRemover = new BlockRemover(this, this.remainingBlocks);
        BallRemover ballRemover = new BallRemover(this, this.ballsCounter);
        borders(ballRemover);
        Rectangle scoreRect = new Rectangle(new geometry.Point(0, 0), this.guiSizeX, 20);
        this.scoreIndicator = new ScoreIndicator(scoreRect, Color.gray, this.counter, this.levelInformation);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.counter);
        this.scoreIndicator.addToGame(this);
        List<Block> blockList = levelInformation.blocks();
        for (int i = 0; i < blockList.size(); i++) {
            blockList.get(i).addToGame(this);
            blockList.get(i).addHitListener(blockRemover);
            blockList.get(i).addHitListener(scoreTrackingListener);
            this.remainingBlocks.increase(1);
        }
        geometry.Rectangle rect = new geometry.Rectangle(new geometry.Point(MIDDLE_X
                - (levelInformation.paddleWidth() / 2), PADDLE_START_Y), levelInformation.paddleWidth(),
                HEIGHT_OF_PADDLE);
        Velocity velocity = new Velocity(levelInformation.paddleSpeed(), 0);
        Paddle paddle = new Paddle(rect, Color.BLUE, this.keyboard, velocity);
        paddle.addToGame(this);
        this.createBallsOnTopOfPaddle();

    }

    /**
     * Creates the balls on top of the paddle according to the level information.
     */

    public void createBallsOnTopOfPaddle() {
        int numBalls = levelInformation.numberOfBalls();
        for (int i = 0; i < numBalls; i++) {
            Ball ball = new Ball(MIDDLE_X, BALL_START_Y, BALL_RADUIS, Color.green, this.environment);
            ball.setVelocity(levelInformation.initialBallVelocities().get(i));
            ball.addToGame(this);
            ballsCounter.increase(1);
        }

    }

    // Run the game -- start the animation loop.

    /**
     * This class represents a game.
     * It contains a game loop which updates and draws all the sprites in the game.
     * The game loop runs until the game is over or the window is closed.
     */
    public void run() {

        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(new CountdownAnimation(2, 3, sprites));
        this.runner.run(this);
//       gui.close();


    }


}
//
//    /**
//     * Main method that creates a new game, initializes it and starts the game loop.
//     *
//     * @param args Arguments passed to the program
//     */
//
//    public static void main(String[] args) {
//        Game game = new Game(GUI_SIZE_X, GUI_SIZE_Y);
//        game.initialize();
//        game.run();
//    }

