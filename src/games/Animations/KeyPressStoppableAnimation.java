// 211604343 Shaked Gitta
package games.Animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The KeyPressStoppableAnimation class represents an animation that can be stopped by pressing a key.
 * It implements the Animation interface.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private Boolean isAlredyPressed;
    private boolean stop;
    /**
     * Constructs a KeyPressStoppableAnimation instance with the given keyboard sensor, key, and animation.
     *
     * @param sensor    The keyboard sensor to listen for key presses.
     * @param key       The key that can stop the animation.
     * @param animation The animation to be displayed.
     */

    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.animation = animation;
        this.key = key;
        this.keyboardSensor = sensor;
        this.isAlredyPressed = true;
        this.stop = false;
    }

    @Override

    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (this.keyboardSensor.isPressed(key)) {
            if (!isAlredyPressed) {
                this.stop = true;
            }
        } else {
            isAlredyPressed = false;
        }

    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
    // ...
    // think about the implementations of doOneFrame and shouldStop.
}