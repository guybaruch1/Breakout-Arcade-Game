package gui.environments.interfaces;

import biuoop.DrawSurface;
import gui.animation.GameLevel;

/**
 * The interface Sprite.
 *
 * @author Guy Baruch ID 207707472 EX6
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d the d
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * Add to game.
     *
     * @param g the g
     */
    void addToGame(GameLevel g);
}