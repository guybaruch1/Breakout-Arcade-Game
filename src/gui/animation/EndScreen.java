package gui.animation;

import biuoop.DrawSurface;
import gui.environments.interfaces.Animation;

/**
 * The EndScreen class represents the animation displayed at the end of the game.
 * It implements the Animation interface.
 *
 * @author Guy Baruch ID 207707472 EX6
 */
public class EndScreen implements Animation {

    private boolean didWin;
    private int score;

    /**
     * Constructs an EndScreen with the specified parameters.
     *
     * @param didWin Whether the player won the game or not.
     * @param score  The player's score.
     */
    public EndScreen(boolean didWin, int score) {
        this.didWin = didWin;
        this.score = score;
    }

    /**
     * Performs one frame of the end screen animation.
     *
     * @param d The DrawSurface to draw the animation on.
     */
    public void doOneFrame(DrawSurface d) {
        if (didWin) {
            d.drawText(10, d.getHeight() / 3, "You Win! Your score is " + score, 32);
        } else {
            d.drawText(10, d.getHeight() / 3, "Game Over. Your score is " + score, 32);
        }
        d.drawText(10, d.getHeight() / 2, "Press space to exit", 32);
    }

    /**
     * Checks if the end screen animation should stop.
     *
     * @return true if the animation should stop, false otherwise.
     */
    public boolean shouldStop() {
        return false;
    }
}