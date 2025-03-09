package gui.environments.objects;

import biuoop.DrawSurface;
import gui.environments.interfaces.Collidable;
import gui.environments.interfaces.Sprite;
import gui.graphics.Rectangle;
import gui.graphics.Velocity;
import gui.graphics.Point;
import gui.animation.GameLevel;
import numbers.DoubleEquals;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * This class represents a paddle in a game.
 * It implements the Sprite and Collidable interfaces.
 *
 * @author Guy Baruch ID 207707472 EX6
 */
public class Paddle implements Sprite, Collidable {
    /**
     * The angel in the 3 region.
     */
    public static final int ANGLE_3 = 270;
    /**
     * The angel in the 2 region.
     */
    public static final int ANGLE_2 = 240;
    /**
     * The angel in the 1 region.
     */
    public static final int ANGLE_1 = 210;
    /**
     * The angel in the 4 region.
     */
    public static final int ANGLE_4 = 300;
    /**
     * The angel in the 5 region.
     */
    public static final int ANGLE_5 = 330;
    /**
     * Power by two.
     */
    public static final int POW = 2;
    /**
     * The regions size.
     */
    public static final int REGIONS_SIZE = 5;
    /**
     * The constant REGION_2.
     */
    public static final int REGION_2 = 2;
    /**
     * The constant REGION_3.
     */
    public static final int REGION_3 = 3;
    /**
     * The constant REGION_4.
     */
    public static final int REGION_4 = 4;
    /**
     * The constant REGION_5.
     */
    public static final int REGION_5 = 5;

    // Fields
    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddle;
    private double leftFrame;
    private double rightFrame;
    private double speed;

    /**
     * Instantiates a new Paddle.
     *
     * @param rect     rectangle
     * @param keyboard the keyboard
     */
    public Paddle(Rectangle rect, biuoop.KeyboardSensor keyboard) {
        this.paddle = rect;
        this.keyboard = keyboard;
    }

    /**
     * Sets speed.
     *
     * @param speed the speed
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * Sets frame to restrict the paddle.
     *
     * @param leftFrame  the left frame
     * @param rightFrame the right frame
     */
    public void frame(double leftFrame, double rightFrame) {
        this.leftFrame = leftFrame;
        this.rightFrame = rightFrame;
    }

    /**
     * Move one step.
     */
    public void moveOneStep() {
        // move left
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        // move right
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * Move left.
     */
    public void moveLeft() {
        if (this.paddle.getUpperLeft().getX() > leftFrame) {
            Point point = new Point(this.paddle.getUpperLeft().getX()
                    - this.speed, this.paddle.getUpperRight().getY());
            this.paddle = new Rectangle(point, this.paddle.getWidth(), this.paddle.getHeight());
        }
    }

    /**
     * Move right.
     */
    public void moveRight() {
        if (this.paddle.getUpperRight().getX() < rightFrame) {
            Point point = new Point(this.paddle.getUpperLeft().getX()
                    + this.speed, this.paddle.getUpperRight().getY());
            this.paddle = new Rectangle(point, this.paddle.getWidth(), this.paddle.getHeight());
        }
    }

    /**
     * This method is called whenever time passes in the game.
     * It moves the paddle one step.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * This method draws the paddle on a given DrawSurface.
     *
     * @param d The DrawSurface to draw the paddle on.
     */
    public void drawOn(DrawSurface d) {
        // Fill a rectangle representing the paddle on the DrawSurface
        d.setColor(Color.DARK_GRAY);
        d.fillRectangle((int) this.paddle.getUpperLeft().getX(), (int) this.paddle.getUpperLeft().getY(),
                (int) this.paddle.getWidth(), (int) this.paddle.getHeight());
    }

    /**
     * This method returns the collision rectangle of the paddle.
     *
     * @return The collision rectangle of the paddle.
     */
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }

    /**
     * This method calculates the new velocity of a ball after it hits a paddle.
     *
     * @param collisionPoint  the point where the ball collides with the paddle
     * @param currentVelocity the current velocity of the ball
     * @param hitter          the ball
     * @return the new velocity of the ball after hitting the paddle
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // the vectors of the velocity of the ball
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        // the X bound of the frame from the left
        double leftX = this.paddle.getUpperLeft().getX();
        // the X bound of the frame from the right
        double rightX = this.paddle.getUpperRight().getX();
        // the Y bound of the frame from up
        double upY = this.paddle.getUpperLeft().getY();
        // the Y bound of the frame from down
        double downY = this.paddle.getLowerLeft().getY();

        // calculate regions on paddle for different angles of deflection
        double regions = this.paddle.getWidth() / REGIONS_SIZE;
        double region1 = regions + this.paddle.getUpperLeft().getX();
        double region2 = REGION_2 * regions + this.paddle.getUpperLeft().getX();
        double region3 = REGION_3 * regions + this.paddle.getUpperLeft().getX();
        double region4 = REGION_4 * regions + this.paddle.getUpperLeft().getX();
        double region5 = REGION_5 * regions + this.paddle.getUpperLeft().getX();

        // if the ball hit a corner, change both dx and dy direction
        if (((DoubleEquals.equals(collisionPoint.getX(), rightX))
                || (DoubleEquals.equals(collisionPoint.getX(), leftX)))
                && ((DoubleEquals.equals(collisionPoint.getY(), downY))
                || (DoubleEquals.equals(collisionPoint.getY(), upY)))) {
            return new Velocity(-dx, -dy);
        }

        // if ball hit left or right side, change dx direction
        if ((DoubleEquals.equals(collisionPoint.getX(), rightX)) || (DoubleEquals.equals(collisionPoint.getX(),
                leftX))) {
            return new Velocity(-dx, dy);

            // if ball hit top or bottom side, change dy direction and angle based on region hit
        } else if ((DoubleEquals.equals(collisionPoint.getY(), downY)) || (DoubleEquals.equals(collisionPoint.getY(),
                upY))) {
            double speed = Math.sqrt((Math.pow(dx, POW) + Math.pow(dy, POW)));
            if (collisionPoint.getX() < region1 || DoubleEquals.equals(collisionPoint.getX(), region1)) {
                return Velocity.fromAngleAndSpeed(ANGLE_1, speed);
            }
            if (collisionPoint.getX() < region2 || DoubleEquals.equals(collisionPoint.getX(), region2)) {
                return Velocity.fromAngleAndSpeed(ANGLE_2, speed);
            }
            if (collisionPoint.getX() < region3 || DoubleEquals.equals(collisionPoint.getX(), region3)) {
                return Velocity.fromAngleAndSpeed(ANGLE_3, speed);
            }
            if (collisionPoint.getX() < region4 || DoubleEquals.equals(collisionPoint.getX(), region4)) {
                return Velocity.fromAngleAndSpeed(ANGLE_4, speed);
            }
            if (collisionPoint.getX() < region5 || DoubleEquals.equals(collisionPoint.getX(), region5)) {
                return Velocity.fromAngleAndSpeed(ANGLE_5, speed);
            }
        }
        // change the value of the center point of the ball
        return currentVelocity;
    }

    /**
     * This method adds the paddle to a game as a sprite and a collidable object.
     *
     * @param g the game to add the paddle to
     */
    public void addToGame(GameLevel g) {
        // add the paddle as a sprite to the game
        g.addSprite(this);
        // add the paddle as a collidable object to the game
        g.addCollidable(this);
    }

}