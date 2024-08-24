// 211604343 Shaked Gitta

package games.Levels;

import biuoop.KeyboardSensor;

import games.Animations.AnimationRunner;
import games.Animations.KeyPressStoppableAnimation;
import games.Animations.WinningScreen;
import games.Counter;
import games.GameLevel;

import java.util.ArrayList;
import java.util.List;

/**
 * The GameFlow class is responsible for running the levels of the game and managing the transitions between levels.
 */
public class GameFlow {

    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private ArrayList<LevelInformation> levelInformationList;
    private Counter counter;

    /**
     * Constructs a GameFlow object.
     *
     * @param ar The AnimationRunner responsible for running animations.
     * @param ks The KeyboardSensor responsible for receiving keyboard input.
     */

    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.levelInformationList = new ArrayList<>();
        this.counter = new Counter();


    }

    /**
     * Runs the given list of LevelInformation objects as a sequence of levels.
     *
     * @param levels The list of LevelInformation objects representing the levels to be played.
     */

    public void runLevels(List<LevelInformation> levels) {
        // ...
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo,
                    this.keyboardSensor,
                    this.animationRunner, counter);

            level.initialize();
            int num1 = level.getRemainingBlocks();
            int num2 = level.getBallsCounter();

            while (level.getBallsCounter() > 0 && level.getRemainingBlocks() > 0) {
                level.run();
            }
            if (level.getBallsCounter() == 0) {
                break;

            }


        }
        WinningScreen winningScreen = new WinningScreen(counter.getValue(), this.keyboardSensor);
        KeyPressStoppableAnimation keyPress = new KeyPressStoppableAnimation(this.keyboardSensor,
                KeyboardSensor.SPACE_KEY, winningScreen);
        this.animationRunner.run(keyPress);
        animationRunner.getGui().close();

    }
}

