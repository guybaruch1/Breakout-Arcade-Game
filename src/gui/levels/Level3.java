package gui.levels;

import gui.environments.interfaces.LevelInformation;
import gui.environments.interfaces.Sprite;
import gui.environments.objects.Block;
import gui.graphics.Rectangle;
import gui.graphics.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The Level3 class represents the third level of the game.
 * It implements the LevelInformation interface to provide information about the level's properties.
 *
 * @author Guy Baruch ID 207707472 EX6
 */
public class Level3 implements LevelInformation {
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
     * The Num of balls.
     */
    static final int NUM_OF_BALLS = 10;

    @Override
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballVelocity = new ArrayList<>();
        ballVelocity.add(new Velocity(5, -6));
        ballVelocity.add(new Velocity(-5, -6));

        return ballVelocity;
    }

    @Override
    public int paddleSpeed() {
        return 15;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "HARD";
    }

    @Override
    public Sprite getBackground() {
        return new Background3();
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
        double startBlockLocX = WIDTH - FRAME_LEN - BLOCK_WIDTH;
        double startBlockLocY = HEIGHT / QUARTER;
        double blockLocX = startBlockLocX;
        double blockLocY = startBlockLocY;

        Color[] colors = {Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.PINK, Color.GREEN};
        int[] quantities = {GRAY, RED, YELLOW, BLUE, PINK, GREEN};

        for (int colorIndex = 0; colorIndex < colors.length; colorIndex++) {
            for (int i = 0; i < quantities[colorIndex]; i++) {
                blockList.add(initBlock(blockLocX, blockLocY, colors[colorIndex]));
                blockLocX -= BLOCK_WIDTH;
            }
            blockLocY += BLOCK_HEIGHT;
            blockLocX = startBlockLocX;
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }
}
