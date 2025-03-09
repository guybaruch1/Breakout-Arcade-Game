package gui.animation;

import biuoop.DrawSurface;
import gui.environments.interfaces.Animation;

/**
 * The PauseScreen class is responsible for displaying a pause screen in the game.
 * It implements the Animation interface.
 *
 * @author Guy Baruch ID 207707472 EX6
 */
public class PauseScreen implements Animation {

    @Override
    public void doOneFrame(DrawSurface d) {
        // Display the pause message on the screen
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        // The animation should not stop until the user presses the space key to continue
        return false;
    }
}