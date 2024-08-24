import biuoop.GUI;
import biuoop.KeyboardSensor;
import games.Animations.AnimationRunner;
import games.Levels.LevelInformation;
import games.Levels.GameFlow;
import games.Levels.Level1;
import games.Levels.Level2;
import games.Levels.Level3;

import java.util.ArrayList;

/**
 * The Ass5Game class is responsible for running the game by creating an instance of the Game class, initializing it and
 * starting the game loop.
 */
public class Ass6Game {
    private static final int GUI_SIZE_X = 800;
    private static final int GUI_SIZE_Y = 600;

    /**
     * The main method creates an instance of the Game class, initializes it and starts the game loop.
     *
     * @param args array of string arguments that can be passed to the main method.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid game", GUI_SIZE_X, GUI_SIZE_Y);
        AnimationRunner runner = new AnimationRunner(gui, 60);
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        GameFlow gameFlow = new GameFlow(runner, keyboard);
        ArrayList<LevelInformation> levelInformationList = new ArrayList<>();
        LevelInformation level1 = new Level1();

        LevelInformation level2 = new Level2();

        LevelInformation level3 = new Level3();

        if (args.length == 0) {
            levelInformationList.add(level1);
            levelInformationList.add(level2);
            levelInformationList.add(level3);
        } else {
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("1")) {
                    levelInformationList.add(level1);

                } else if (args[i].equals("2")) {
                    levelInformationList.add(level2);

                } else if (args[i].equals("3")) {
                    levelInformationList.add(level3);

                }
            }
        }
        gameFlow.runLevels(levelInformationList);

//        LevelInformation level3 = new Level3();
//        GameLevel game = new GameLevel(level3, keyboard, runner);
//        game.initialize();
//        game.run();


    }
}
