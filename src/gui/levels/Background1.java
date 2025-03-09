package gui.levels;

import biuoop.DrawSurface;
import gui.animation.GameLevel;
import gui.environments.interfaces.Sprite;

import java.awt.Color;

/**
 * The Background1 class represents a custom background for a game level.
 * It draws various shapes and lines on the game surface to create the background.
 *
 * @author Guy Baruch ID 207707472 EX6
 */
public class Background1 implements Sprite {
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;

    /**
     * Constructs a new Background1 object.
     */
    public Background1() {
    }

    /**
     * Draws the background on the given DrawSurface.
     *
     * @param d the DrawSurface to draw on
     */
    @Override
    public void drawOn(DrawSurface d) {
        // Set the color of the block and fill its rectangle on the surface
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

        // Draw circles with different colors and sizes
        d.setColor(Color.RED);
        d.drawCircle(400, 200, 100);
        d.setColor(Color.MAGENTA);
        d.drawCircle(400, 200, 75);
        d.setColor(Color.PINK);
        d.drawCircle(400, 200, 50);

        // Draw lines
        d.setColor(Color.WHITE);
        d.drawLine(270, 200, 370, 200);
        d.drawLine(430, 200, 530, 200);
        d.drawLine(400, 70, 400, 170);
        d.drawLine(400, 230, 400, 330);
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