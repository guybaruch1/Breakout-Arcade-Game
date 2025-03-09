package gui.animation;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import gui.environments.collections.SpriteCollection;
import gui.environments.interfaces.Animation;

import java.awt.Color;

/**
 * The CountdownAnimation class represents a countdown animation.
 * It implements the Animation interface.
 *
 * @author Guy Baruch ID 207707472 EX6
 */
public class CountdownAnimation implements Animation {
    private final double numOfSeconds;
    private final int countFrom;
    private int countDown;
    private final SpriteCollection gameScreen;
    private boolean stop;
    private static final int TEXT_SIZE = 80;

    /**
     * Constructs a CountdownAnimation with the specified parameters.
     *
     * @param numOfSeconds The total number of seconds for the countdown.
     * @param countFrom    The starting count value.
     * @param gameScreen   The SpriteCollection for drawing the game screen.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.countDown = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
    }

    /**
     * Performs one frame of the countdown animation.
     *
     * @param d The DrawSurface to draw the animation on.
     */
    public void doOneFrame(DrawSurface d) {
        // Check if the countdown reached 0
        if (this.countDown < 0) {
            this.stop = true;
        }

        // Draw the game screen and level name
        gameScreen.drawAllOn(d);

        // Draw the countdown value
        d.setColor(Color.magenta);
        if (this.countDown == 0) {
            d.drawText(330, d.getHeight() / 2, "GO!", TEXT_SIZE);
        } else if (this.countDown == -1) {
            d.drawText(380, d.getHeight() / 2, "", TEXT_SIZE);
        } else {
            d.drawText(380, d.getHeight() / 2, Integer.toString(countDown), TEXT_SIZE);
        }

        if (countDown != countFrom) {
            // Sleep for numOfSeconds seconds
            new Sleeper().sleepFor((long) (1000 * (numOfSeconds / countFrom)));
        }

        // Decrease the count
        this.countDown--;
    }

    /**
     * Checks if the countdown animation should stop.
     *
     * @return true if the animation should stop, false otherwise.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}