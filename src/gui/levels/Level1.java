package gui.levels;

import gui.environments.interfaces.LevelInformation;
import gui.environments.interfaces.Sprite;
import gui.environments.objects.Block;
import gui.graphics.Point;
import gui.graphics.Rectangle;
import gui.graphics.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The Level1 class represents the first level of the game.
 * It implements the LevelInformation interface to provide information about the level's properties.
 *
 * @author Guy Baruch ID 207707472 EX6
 */
public class Level1 implements LevelInformation {
    /**
     * The blocks' width.
     */
    public static final int BLOCK_WIDTH = 60;
    /**
     * The blocks' height.
     */
    public static final int BLOCK_HEIGHT = 60;

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballVelocity = new ArrayList<>();
        ballVelocity.add(new Velocity(0, -6));
        return ballVelocity;
    }

    @Override
    public int paddleSpeed() {
        return 12;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Direct hit";
    }

    @Override
    public Sprite getBackground() {
        return new Background1();
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
        Point startPoint = new Point(blockLocX, blockLocY);
        Block block = new Block(new Rectangle(startPoint, BLOCK_WIDTH, BLOCK_HEIGHT));
        block.setColor(color);
        return block;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<>();
        // initialize the block
        double startBlockLocX = 370;
        double startBlockLocY = 170;
        double blockLocX = startBlockLocX;
        double blockLocY = startBlockLocY;
        blockList.add(initBlock(blockLocX, blockLocY, Color.RED));
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }
}
