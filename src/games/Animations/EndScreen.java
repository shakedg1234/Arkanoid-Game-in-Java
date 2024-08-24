// 211604343 Shaked Gitta
package games.Animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * The EndScreen class represents the animation displayed at the end of the game.
 * It implements the Animation interface.
 */

public class EndScreen implements Animation {
    private int score;
    private KeyboardSensor keyboard;
    private boolean stop;
    /**
     * Constructs an EndScreen instance with the given score and keyboard sensor.
     *
     * @param score    The final score of the player.
     * @param k The keyboard sensor to listen for key presses.
     */
    public EndScreen(int score, KeyboardSensor k) {
        this.score = score;
        this.keyboard = k;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        String write = "Game Over. Your score is :" + Integer.toString(score);
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
