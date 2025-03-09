package gui.environments.interfaces;

import gui.environments.objects.Block;
import gui.graphics.Velocity;

import java.util.List;

/**
 * The LevelInformation interface represents the information required to describe a game level.
 * It provides methods to access various properties of the level such as the number of balls, paddle speed,
 * paddle width, level name, background sprite, blocks, and number of blocks to remove.
 *
 * @author Guy Baruch ID 207707472 EX6
 */
public interface LevelInformation {
    /**
     * Returns the number of balls in the level.
     *
     * @return the number of balls
     */
    int numberOfBalls();

    /**
     * Returns the initial velocities of each ball in the level.
     * The size of the returned list should be equal to the number of balls.
     *
     * @return a list of ball velocities
     */
    List<Velocity> initialBallVelocities();

    /**
     * Returns the speed of the paddle in pixels per frame.
     *
     * @return the paddle speed
     */
    int paddleSpeed();

    /**
     * Returns the width of the paddle in pixels.
     *
     * @return the paddle width
     */
    int paddleWidth();

    /**
     * Returns the name of the level.
     * This name will be displayed at the top of the screen.
     *
     * @return the level name
     */
    String levelName();

    /**
     * Returns the background sprite of the level.
     *
     * @return the background sprite
     */
    Sprite getBackground();

    /**
     * Returns the blocks that make up this level.
     * Each block contains its size, color, and location.
     *
     * @return a list of blocks
     */
    List<Block> blocks();

    /**
     * Returns the number of blocks that should be removed
     * before the level is considered to be cleared.
     * This number should be less than or equal to the total number of blocks in the level.
     *
     * @return the number of blocks to remove
     */
    int numberOfBlocksToRemove();
}