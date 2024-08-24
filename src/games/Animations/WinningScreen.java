// 211604343 Shaked Gitta
package games.Animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * The WinningScreen class represents an animation for the winning screen in the game.
 * It implements the Animation interface.
 */
public class WinningScreen implements Animation {
    private int score;
    private KeyboardSensor keyboard;
    private boolean stop;
    /**
     * Constructs a WinningScreen instance with the given score and keyboard sensor.
     *
     * @param score    The score to display on the winning screen.
     * @param k The keyboard sensor to listen for input.
     */

    public WinningScreen(int score, KeyboardSensor k) {
        this.score = score;
        this.keyboard = k;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        String write = "You Win! Your score is:" + Integer.toString(score);
        d.drawText(10, d.getHeight() / 2, write, 32);
//        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
//            this.stop = true;
//        }
    }

    @Override

    public boolean shouldStop() {
        return this.stop;

    }

}
