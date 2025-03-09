package gui.animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import gui.environments.collections.GameEnvironment;
import gui.environments.collections.SpriteCollection;
import gui.environments.hitting.BlockRemover;
import gui.environments.hitting.BallRemover;
import gui.environments.hitting.ScoreTrackingListener;
import gui.environments.hitting.ScoreIndicator;
import gui.environments.hitting.Counter;
import gui.environments.hitting.NameLevel;
import gui.environments.interfaces.Animation;
import gui.environments.interfaces.Collidable;
import gui.environments.interfaces.LevelInformation;
import gui.environments.interfaces.Sprite;
import gui.environments.objects.Ball;
import gui.environments.objects.Block;
import gui.environments.objects.Paddle;
import gui.graphics.Rectangle;
import gui.graphics.Point;
import gui.graphics.Velocity;

import java.awt.Color;

/**
 * The Game.
 *
 * @author Guy Baruch ID 207707472 EX6
 */
public class GameLevel implements Animation {
    /**
     * the width of the window.
     */
    public static final int WIDTH = 800;
    /**
     * the height of the window.
     */
    public static final int HEIGHT = 600;
    /**
     * The radius of the ball.
     */
    public static final int BALL_RADIUS = 5;
    /**
     * The X value of the start point.
     */
    public static final int START_BALL_X_1 = 400;
    /**
     * The Y value of the start point.
     */
    public static final int START_BALL_Y_1 = 500;
    /**
     * The paddle height.
     */
    public static final int PADDLE_HEIGHT = 15;
    /**
     * The length of the frame.
     */
    public static final int FRAME_LEN = 20;
    /**
     * The upper left X value of the blocks.
     */
    public static final int UPPER_LEFT_X = 0;
    /**
     * The upper left Y value of the blocks.
     */
    public static final int UPPER_LEFT_Y = 0;

    // Fields
    private AnimationRunner runner;
    private boolean running;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter currentScore;
    private KeyboardSensor keyboard;
    private LevelInformation levelInformation;

    /**
     * Instantiates a new Game level.
     *
     * @param levelInformation the level information
     */
    public GameLevel(LevelInformation levelInformation) {
        this.runner = new AnimationRunner(60, new Sleeper());
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.remainingBlocks = new Counter();
        this.remainingBalls = new Counter();
        this.currentScore = new Counter();
        this.levelInformation = levelInformation;
    }

    /**
     * Instantiates a new Game level.
     *
     * @param levelInformation the level information
     * @param currentScore     the current score
     * @param keyboard         the keyboard
     * @param runner           the runner
     */
    public GameLevel(LevelInformation levelInformation, Counter currentScore, KeyboardSensor keyboard,
                     AnimationRunner runner) {
        this.levelInformation = levelInformation;
        this.keyboard = keyboard;
        this.runner = runner;
        this.currentScore = currentScore;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.remainingBlocks = new Counter();
        this.remainingBalls = new Counter();
    }

    /**
     * Add a Collidable object to the game environment.
     *
     * @param c the Collidable object to add to the environment
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Remove a Collidable object from the game environment.
     *
     * @param c the Collidable object to add to the environment
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Add the given sprite to the game's sprite collection.
     *
     * @param s the sprite to be removed
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Remove the given sprite from the game's sprite collection.
     *
     * @param s the sprite to be removed
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    private void initializeWallBlock(Point position, double width, double height, Color color) {
        Block wall = new Block(new Rectangle(position, width, height));
        wall.setColor(color);
        this.environment.addCollidable(wall);
        wall.addToGame(this);
    }

    private void initializeWallBlockKiller(Point position, double width, double height, Color color,
                                           BallRemover ballRemover) {
        Block wall = new Block(new Rectangle(position, width, height));
        wall.setColor(color);
        this.environment.addCollidable(wall);
        wall.addToGame(this);
        wall.addHitListener(ballRemover);
    }

    private void initializeBall(Point startPoint, Velocity velocity, Color color) {
        Ball ball = new Ball(startPoint, BALL_RADIUS, color);
        this.remainingBalls.increase(1);
        ball.setVelocity(velocity);
        ball.addToGame(this);
        ball.setGameEnvironment(this.environment);
    }

    /**
     * Initializes the game by setting up the ball, blocks, walls, and paddle.
     */
    public void initialize() {
        // set background
        Sprite background = levelInformation.getBackground();
        background.addToGame(this);

        BlockRemover blockRemover = new BlockRemover(this, this.remainingBlocks);
        BallRemover ballRemover = new BallRemover(this, this.remainingBalls);
        ScoreTrackingListener scoreTracking = new ScoreTrackingListener(this.currentScore, this.remainingBlocks);
        ScoreIndicator scoreIndicator = new ScoreIndicator(350, 15, this.currentScore, 15);
        NameLevel nameLevel = new NameLevel(600, 15, this.levelInformation.levelName(), 15);

        // initialize the balls
        for (Velocity ballVelocity : levelInformation.initialBallVelocities()) {
            Point position = new Point(START_BALL_X_1, START_BALL_Y_1);
            initializeBall(position, ballVelocity, Color.gray);
        }

        for (Block block : levelInformation.blocks()) {
            block.addToGame(this);
            this.remainingBlocks.increase(1);
            block.addHitListener(blockRemover);
            this.environment.addCollidable(block);
            block.addHitListener(scoreTracking);
        }

        // lower wall
        Point downPosition = new Point(UPPER_LEFT_X, HEIGHT + 10);
        initializeWallBlockKiller(downPosition, WIDTH, FRAME_LEN, Color.GRAY, ballRemover);

        // left wall
        Point leftPosition = new Point(UPPER_LEFT_X, UPPER_LEFT_Y + 20);
        initializeWallBlock(leftPosition, FRAME_LEN, HEIGHT, Color.GRAY);

        // right wall
        Point rightPosition = new Point(WIDTH - FRAME_LEN, UPPER_LEFT_Y + 20);
        initializeWallBlock(rightPosition, FRAME_LEN, HEIGHT, Color.GRAY);

        // upper wall
        Point upPosition = new Point(UPPER_LEFT_X, UPPER_LEFT_Y + 20);
        initializeWallBlock(upPosition, WIDTH, FRAME_LEN, Color.GRAY);

        // upper wall score
        Point upScorePosition = new Point(UPPER_LEFT_X, UPPER_LEFT_Y);
        Block upScore = new Block(new Rectangle(upScorePosition, WIDTH, FRAME_LEN));
        upScore.setColor(Color.WHITE);
        upScore.addToGame(this);
        scoreIndicator.addToGame(this);
        nameLevel.addToGame(this);

        // initialize the paddle
        this.keyboard = this.runner.getGui().getKeyboardSensor();
        Paddle paddle = new Paddle(new Rectangle(new Point((double) (WIDTH - levelInformation.paddleWidth()) / 2,
                HEIGHT - FRAME_LEN - PADDLE_HEIGHT), levelInformation.paddleWidth(), PADDLE_HEIGHT), keyboard);
        paddle.frame(UPPER_LEFT_X + FRAME_LEN, WIDTH - FRAME_LEN);
        paddle.setSpeed(levelInformation.paddleSpeed());
        paddle.addToGame(this);
        this.environment.addCollidable(paddle);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // the logic from the previous run method goes here.
        // the `return` or `break` statements should be replaced with
        this.sprites.notifyAllTimePassed();
        this.environment.drawAllOn(d);
        this.sprites.drawAllOn(d);
        //this.gui.show(d);
        if (this.remainingBlocks.getValue() == 0) {
            //this.runner.getGui().close();
            this.running = false;
        }
        if (this.remainingBalls.getValue() == 0) {
            this.running = false;
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    new EndScreen(false, this.currentScore.getValue())));
            this.runner.getGui().close();
        }
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    new PauseScreen()));
            this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        }
    }

    /**
     * Gets remaining balls.
     *
     * @return the remaining balls
     */
    public Counter getRemainingBalls() {
        return this.remainingBalls;
    }

    /**
     * Gets remaining blocks.
     *
     * @return the remaining blocks
     */
    public Counter getRemainingBlocks() {
        return this.remainingBlocks;
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Run.
     */
    public void run() {
        this.running = true;
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);

    }
}
