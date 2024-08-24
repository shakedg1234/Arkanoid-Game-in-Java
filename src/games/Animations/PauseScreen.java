
// 211604343 Shaked Gitta
package games.Animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * The PauseScreen class represents an animation for the pause screen in the game.
 * It implements the Animation interface.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    /**
     * Constructs a PauseScreen instance with the given keyboard sensor.
     *
     * @param k The keyboard sensor to listen for input.
     */

    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
//        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
//            this.stop = true;
//        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
