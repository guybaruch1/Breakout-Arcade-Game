package gui.environments.interfaces;

import biuoop.DrawSurface;
/**
 * The Animation interface represents an animation in the game.
 * An animation is a sequence of frames that are displayed over time.
 *
 * @author Guy Baruch ID 207707472 EX6
 */
public interface Animation {
    /**
     * Perform one frame of the animation.
     *
     * @param d the DrawSurface to draw on
     */
    void doOneFrame(DrawSurface d);

    /**
     * Check if the animation should stop.
     *
     * @return true if the animation should stop, false otherwise
     */
    boolean shouldStop();
}