package gui.levels;

import biuoop.DrawSurface;
import gui.animation.GameLevel;
import gui.environments.interfaces.Sprite;

import java.awt.Color;

/**
 * The Background3 class represents a custom background for a game level.
 * It draws various shapes and rectangles on the game surface to create the background.
 *
 * @author Guy Baruch ID 207707472 EX6
 */
public class Background3 implements Sprite {
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;

    /**
     * Constructs a new Background3 object.
     */
    public Background3() {
    }

    /**
     * Draws the background on the given DrawSurface.
     *
     * @param d the DrawSurface to draw on
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.GREEN.darker());
        d.fillRectangle(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        d.setColor(new Color(153, 153, 153));
        d.fillRectangle(117, 250, 15, 150);
        d.setColor(new Color(102, 102, 102));
        d.fillRectangle(100, 375, 50, 75);
        d.setColor(new Color(51, 51, 51));
        d.fillRectangle(75, 425, 100, 200);
        d.setColor(Color.ORANGE);
        d.fillCircle(123, 250, 15);
    }

    /**
     * Performs an action when time passed.
     * This method does nothing for the background.
     */
    @Override
    public void timePassed() {
        // No-op for this class
    }

    /**
     * Adds the background to the given GameLevel.
     *
     * @param g the GameLevel to add the background to
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}