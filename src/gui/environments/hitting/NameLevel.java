package gui.environments.hitting;

import biuoop.DrawSurface;
import gui.animation.GameLevel;
import gui.environments.interfaces.Sprite;

import java.awt.Color;

/**
 * The NameLevel class is responsible for displaying the name of the level on the screen.
 * It implements the Sprite interface.
 *
 * @author Guy Baruch ID 207707472 EX6
 */
public class NameLevel implements Sprite {
    private String nameLevel;
    private int x;
    private int y;
    private int size;

    /**
     * Constructs a NameLevel object with the specified position, name, and font size.
     *
     * @param x         the x-coordinate of the text position
     * @param y         the y-coordinate of the text position
     * @param nameLevel the name of the level
     * @param size      the font size of the text
     */
    public NameLevel(int x, int y, String nameLevel, int size) {
        this.nameLevel = nameLevel;
        this.x = x;
        this.y = y;
        this.size = size;
    }

    @Override
    public void drawOn(DrawSurface d) {
        // Set the color and draw the level name on the screen
        d.setColor(Color.BLACK);
        d.drawText(this.x, this.y, nameLevel, this.size);
    }

    @Override
    public void timePassed() {
        // No-op for this class
    }

    @Override
    public void addToGame(GameLevel g) {
        // Add this sprite to the game level's sprite collection
        g.addSprite(this);
    }
}