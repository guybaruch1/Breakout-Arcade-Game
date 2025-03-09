package gui.levels;

import gui.environments.interfaces.LevelInformation;
import gui.environments.interfaces.Sprite;
import gui.environments.objects.Block;
import gui.graphics.Rectangle;
import gui.graphics.Velocity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The Level2 class represents the second level of the game.
 * It implements the LevelInformation interface to provide information about the level's properties.
 *
 * @author Guy Baruch ID 207707472 EX6
 */
public class Level2 implements LevelInformation {
    /**
     * the width of the window.
     */
    public static final int WIDTH = 800;
    /**
     * the height of the window.
     */
    public static final int HEIGHT = 600;
    /**
     * The blocks' width.
     */
    public static final int BLOCK_WIDTH = 50;
    /**
     * The blocks' height.
     */
    public static final int BLOCK_HEIGHT = 20;
    /**
     * The length of the frame.
     */
    public static final int FRAME_LEN = 20;
    /**
     * The constant GRAY.
     */
    public static final int GRAY = 12;
    /**
     * The constant RED.
     */
    public static final int RED = 11;
    /**
     * The constant YELLOW.
     */
    public static final int YELLOW = 10;
    /**
     * The constant BLUE.
     */
    public static final int BLUE = 9;
    /**
     * The constant PINK.
     */
    public static final int PINK = 8;
    /**
     * The constant GREEN.
     */
    public static final int GREEN = 7;
    /**
     * The constant QUARTER.
     */
    public static final int QUARTER = 4;
    /**
     * The Level name.
     */
    static final String LEVEL_NAME = "Wide Easy";
    /**
     * The Num of balls.
     */
    static final int NUM_OF_BALLS = 10;
    /**
     * The Initial block num.
     */
    static final int INITIAL_BLOCK_NUM = 15;
    /**
     * The Paddle speed.
     */
    static final int PADDLE_SPEED = 10;
    /**
     * The Paddle width.
     */
    static final int PADDLE_WIDTH = 650;
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;
    private static final int INTERVAL = 2;
    private static final int JUMP = 10;
    private static final int BASE_ANGLE = 215;
    private static final int DEFAULT_BALL_SPEED = 5;

    @Override
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        for (int i = 0; i < NUM_OF_BALLS + INTERVAL; i++) {
            if (i != ((NUM_OF_BALLS + INTERVAL) / INTERVAL) - 1
                    && i != (NUM_OF_BALLS + INTERVAL) / INTERVAL) {
                velocities.add(Velocity.fromAngleAndSpeed(BASE_ANGLE + (i * JUMP),
                        DEFAULT_BALL_SPEED));
            }
        }
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 2;
    }

    @Override
    public int paddleWidth() {
        return 700;
    }

    @Override
    public String levelName() {
        return "Winter";
    }

    @Override
    public Sprite getBackground() {
        return new Background2();
    }

    /**
     * Initializes a block with the specified location and color.
     *
     * @param blockLocX the X-coordinate of the block's location
     * @param blockLocY the Y-coordinate of the block's location
     * @param color     the color of the block
     * @return the initialized block
     */
    public Block initBlock(double blockLocX, double blockLocY, Color color) {
        gui.graphics.Point startPoint = new gui.graphics.Point(blockLocX, blockLocY);
        Block block = new Block(new Rectangle(startPoint, BLOCK_WIDTH, BLOCK_HEIGHT));
        block.setColor(color);
        return block;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<>();
        // initialize the blocks
        double startBlockLocX = WIDTH - FRAME_LEN - BLOCK_WIDTH - 10;
        double startBlockLocY = HEIGHT / 2.0;
        double blockLocX = startBlockLocX;
        double blockLocY = startBlockLocY;

        // Define an array of colors
        Color[] colors = new Color[15];

        // Create a Random object
        Random random = new Random();

        for (int i = 0; i < colors.length; i++) {
            // Generate random RGB values
            int red = random.nextInt(256);
            int green = random.nextInt(256);
            int blue = random.nextInt(256);

            // Create a random color using the RGB values
            colors[i] = Color.getHSBColor(red, green, blue);

            blockList.add(initBlock(blockLocX, blockLocY, colors[i]));
            blockLocX -= BLOCK_WIDTH;
        }

        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }
}
