// 211604343 Shaked Gitta
package games.Animations;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import sprites.SpriteCollection;

import java.awt.Color;

/**
 * The CountdownAnimation class displays a countdown animation on top of a given game screen.
 * It implements the Animation interface.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private Sleeper sleeper;
    private double time;

    /**
     * Constructs a CountdownAnimation instance with the specified duration, countdown value, and game screen.
     *
     * @param numOfSeconds The total duration of the countdown animation in seconds.
     * @param countFrom    The value to count down from.
     * @param gameScreen   The game screen to display the countdown animation on top of.
     */

    public CountdownAnimation(double numOfSeconds,
                              int countFrom,
                              SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.sleeper = new Sleeper();
        this.time = ((this.numOfSeconds / (double) (countFrom)) * 1000);
    }

    @Override

    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.blue);
        d.drawText(10, d.getHeight() / 2, "Game Start in:" + countFrom, 32);
        this.sleeper.sleepFor((long) time);
        this.countFrom--;


    }

    @Override

    public boolean shouldStop() {
        if (countFrom == -1) {
            return true;
        }
        return false;

    }
}