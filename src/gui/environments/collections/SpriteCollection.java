package gui.environments.collections;

import biuoop.DrawSurface;
import gui.environments.interfaces.Sprite;

/**
 * A collection of Sprite objects.
 *
 * @author Guy Baruch ID 207707472 EX6
 */
public class SpriteCollection {
    // List of sprites
    private java.util.List<Sprite> sprites =
            new java.util.ArrayList<>();

    /**
     * Adds a sprite to the collection.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * Remove the given sprite from the game's sprite collection.
     *
     * @param s the sprite to be removed
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * Calls timePassed() on all sprites in the collection.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < this.sprites.size(); i++) {
            this.sprites.get(i).timePassed();
        }
    }

    /**
     * Calls drawOn(d) on all sprites in the collection.
     *
     * @param d the DrawSurface to draw on
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : sprites) {
            sprite.drawOn(d);
        }
    }
}