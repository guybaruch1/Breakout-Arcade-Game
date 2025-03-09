package gui.levels;

import biuoop.DrawSurface;
import gui.animation.GameLevel;
import gui.environments.interfaces.Sprite;

import java.awt.Color;

/**
 * The Background2 class represents a custom background for a game level.
 * It draws various shapes and lines on the game surface to create the background.
 *
 * @author Guy Baruch ID 207707472 EX6
 */
public class Background2 implements Sprite {
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;

    /**
     * Constructs a new Background2 object.
     */
    public Background2() {
    }

    /**
     * Draws the background on the given DrawSurface.
     *
     * @param d the DrawSurface to draw on
     */
    @Override
    public void drawOn(DrawSurface d) {
        // Set the background color using HSB
        float[] hsb = Color.RGBtoHSB(173, 216, 230, null);
        d.setColor(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
        d.fillRectangle(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

        // Draw lines and circles with different colors and positions
        Color color = Color.YELLOW.brighter();
        d.setColor(color);
        for (int i = 0; i < 100; i++) {
            d.drawLine(200, 200, i * 6, 300);
        }

        for (int i = 0; i < 3; i++) {
            d.setColor(color);
            d.fillCircle(200, 170, 90 - i * 10);
        }

        d.setColor(Color.LIGHT_GRAY);
        d.fillCircle(200, 500, 25);
        d.fillCircle(220, 520, 25);
        d.fillCircle(220, 480, 25);
        d.setColor(Color.GRAY.brighter());
        d.fillCircle(248, 480, 25);
        d.fillCircle(258, 520, 25);
        d.fillCircle(275, 500, 25);
        d.setColor(Color.GRAY.brighter());
        for (int i = 0; i < 10; i++) {
            d.drawLine(200 + i * 10, 500, 70 + i * 10, 700);
        }

        d.setColor(Color.LIGHT_GRAY);
        d.fillCircle(200 + 400, 500, 25);
        d.fillCircle(220 + 400, 520, 25);
        d.fillCircle(220 + 400, 480, 25);
        d.setColor(Color.GRAY.brighter());
        d.fillCircle(248 + 400, 480, 25);
        d.fillCircle(258 + 400, 520, 25);
        d.fillCircle(275 + 400, 500, 25);
        d.setColor(Color.GRAY.brighter());
        for (int i = 0; i < 10; i++) {
            d.drawLine(200 + 400 + i * 10, 500, 200 + i * 10, 900);
        }
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
