package gui.animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import gui.environments.interfaces.Animation;

/**
 * The AnimationRunner class is responsible for running animations on the GUI.
 *
 * @author Guy Baruch ID 207707472 EX6
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * Constructs an AnimationRunner with the specified frames per second and Sleeper.
     *
     * @param framesPerSecond The number of frames per second to run the animation.
     * @param sleeper         The Sleeper object for controlling the animation speed.
     */
    public AnimationRunner(int framesPerSecond, Sleeper sleeper) {
        this.gui = new GUI("title", 800, 600);
        this.framesPerSecond = framesPerSecond;
        this.sleeper = sleeper;
    }

    /**
     * Returns the GUI associated with the AnimationRunner.
     *
     * @return The GUI.
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * Runs the specified animation.
     *
     * @param animation The animation to run.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // Timing

            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d); // Perform one frame of the animation

            gui.show(d);

            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep); // Sleep to control frame rate
            }
        }
    }
}