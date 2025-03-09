package gui.environments.hitting;

import biuoop.DrawSurface;
import gui.environments.interfaces.Sprite;
import gui.animation.GameLevel;

import java.awt.Color;

/**
 * The ScoreIndicator class represents a sprite that displays the current score on the game screen.
 * It implements the Sprite interface.
 *
 * @author Guy Baruch ID 207707472 EX6
 */
public class ScoreIndicator implements Sprite {
    private Counter currentScore;
    private int x;
    private int y;
    private int size;

    /**
     * Constructs a ScoreIndicator object with the specified position, current score, and size.
     *
     * @param x            The x-coordinate of the score indicator's position.
     * @param y            The y-coordinate of the score indicator's position.
     * @param currentScore The counter representing the current score.
     * @param size         The font size of the score indicator text.
     */
    public ScoreIndicator(int x, int y, Counter currentScore, int size) {
        this.currentScore = currentScore;
        this.x = x;
        this.y = y;
        this.size = size;
    }

    /**
     * Draws the score indicator on the given draw surface.
     *
     * @param d The draw surface on which to draw the score indicator.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(this.x, this.y, "Score: " + currentScore.getValue(), this.size);
    }

    /**
     * Updates the score indicator (no-op for this class).
     */
    @Override
    public void timePassed() {
        // No-op for this class
    }

    /**
     * Adds the score indicator to the given game.
     *
     * @param g The game to which the score indicator will be added.
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
