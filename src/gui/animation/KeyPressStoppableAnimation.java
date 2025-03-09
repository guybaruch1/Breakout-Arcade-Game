package gui.animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gui.environments.interfaces.Animation;

/**
 * The KeyPressStoppableAnimation class is responsible for running an animation until a specific key is pressed.
 * It implements the Animation interface.
 *
 * @author Guy Baruch ID 207707472 EX6
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Constructs a KeyPressStoppableAnimation object with the given parameters.
     *
     * @param sensor    The keyboard sensor to check for key presses.
     * @param key       The key that will stop the animation when pressed.
     * @param animation The animation to run until the key is pressed.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (!this.isAlreadyPressed && this.sensor.isPressed(key)) {
            this.stop = true;
        }
        this.isAlreadyPressed = false;
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}