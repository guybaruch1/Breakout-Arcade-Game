package gui.environments.objects;

import biuoop.DrawSurface;
import gui.environments.hitting.CollisionInfo;
import gui.environments.interfaces.HitListener;
import gui.environments.interfaces.HitNotifier;
import gui.environments.interfaces.Sprite;
import gui.environments.collections.GameEnvironment;
import gui.graphics.Point;
import gui.graphics.Velocity;
import gui.graphics.Line;
import gui.animation.GameLevel;
import numbers.DoubleEquals;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Ball.
 *
 * @author Guy Baruch ID 207707472 EX6
 */
public class Ball implements Sprite, HitNotifier {

    /**
     * One step.
     */
    public static final int ONE_STEP = 1;
    /**
     * The constant FRAMEX.
     */
    public static final int FRAMEX = 200;
    /**
     * The constant FRAMEY.
     */
    public static final int FRAMEY = 200;
    // Fields
    private Point center;
    private final int r;
    private final java.awt.Color color;
    private Velocity v = new Velocity(0, 0);
    private Point framePoint = new Point(0, 0);
    private double frameX = FRAMEX;
    private double frameY = FRAMEY;
    private java.awt.Color backColor;
    private GameEnvironment environment = new GameEnvironment();
    private List<HitListener> hitListeners = new ArrayList<>();

    /**
     * Instantiates a new Ball.
     *
     * @param center the center of the ball
     * @param r      the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * Instantiates a new Ball.
     *
     * @param x     the x value of the center point of the ball
     * @param y     the y value of the center point of the ball
     * @param r     the radius of the ball
     * @param color the color of the ball
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
    }

    /**
     * Gets the x value of the center point of the ball.
     *
     * @return the x value of the center point of the ball
     */
// accessors
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Gets the y value of the center point of the ball.
     *
     * @return the y value of the center point of the ball
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Gets the size of the ball (the radius of the ball).
     *
     * @return the size
     */
    public int getSize() {
        return this.r;
    }

    /**
     * Gets the color of the ball.
     *
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * draws the ball on the given DrawSurface.
     *
     * @param surface the surface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(getX(), getY(), getSize());
    }

    /**
     * Sets velocity.
     *
     * @param v velocity of the ball
     */
    public void setVelocity(Velocity v) {
        this.v = v;
    }

    /**
     * Sets velocity.
     *
     * @param dx the dx value
     * @param dy the dy value
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * returns the velocity of the ball.
     *
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * Sets bound frame to the ball.
     *
     * @param x      the x value of the top left point of the bound frame
     * @param y      the y value of the top left point of the bound frame
     * @param frameX the width of the frame
     * @param frameY the height of the frame
     */
    public void setBound(double x, double y, double frameX, double frameY) {
        this.framePoint = new Point(x, y);
        this.frameX = frameX;
        this.frameY = frameY;
    }

    /**
     * Sets bound frame to the ball.
     *
     * @param p      the top left point of the bound frame
     * @param frameX the width of the frame
     * @param frameY the height of the frame
     */
    public void setBound(Point p, double frameX, double frameY) {
        this.framePoint = p;
        this.frameX = frameX;
        this.frameY = frameY;
    }

    /**
     * Sets background color to the ball frame.
     *
     * @param backColor the background color
     */
    public void setBackColor(java.awt.Color backColor) {
        this.backColor = backColor;
    }

    /**
     * Gets background color to the ball frame.
     *
     * @return the background color
     */
    public java.awt.Color getBackColor() {
        return this.backColor;
    }

    /**
     * Moves the ball one step according to its current velocity, while also checking for collisions with other objects
     * in the environment. If a collision occurs, the ball's position and velocity will be adjusted accordingly.
     */
    public void moveOneStep() {
        // Calculate the trajectory of the ball based on its current position and velocity
        Line trajectory = new Line(this.center, this.getVelocity().applyToPoint(this.center));

        // Check if the ball collides with an object in the environment
        CollisionInfo collideObjInfo = this.environment.getClosestCollision(trajectory);

        if (collideObjInfo != null) {
            // If the ball collides with an object, update its position and velocity
            Point collisionPoint = collideObjInfo.collisionPoint();
            adjustPosition(collisionPoint);
            updateVelocity(collideObjInfo);
        } else {
            // If the ball does not collide with an object, update its position
            this.center = this.getVelocity().applyToPoint(this.center);
        }
    }

    /**
     * Adjusts the position of the ball after a collision with an object in the environment.
     * The position of the ball is adjusted according to the trajectory of the ball before the collision,
     * to ensure that the ball is not overlapping with the object it collided with.
     *
     * @param collisionPoint The point of collision with another object.
     */
    private void adjustPosition(Point collisionPoint) {
        // Create a trajectory line from the current center of the ball to the point where the ball will collide
        Line trajectory = new Line(this.center, this.getVelocity().applyToPoint(this.center));

        // Adjust the position of the ball according to the trajectory
        if (trajectory.start().getX() > trajectory.end().getX()
                && trajectory.start().getY() > trajectory.end().getY()) {
            // Adjust position if the ball was moving up and to the left
            this.center = new Point(collisionPoint.getX() + ONE_STEP, collisionPoint.getY() + ONE_STEP);

        } else if (trajectory.start().getX() < trajectory.end().getX()
                && trajectory.start().getY() < trajectory.end().getY()) {
            // Adjust position if the ball was moving down and to the right
            this.center = new Point(collisionPoint.getX() - ONE_STEP, collisionPoint.getY() - ONE_STEP);

        } else if (trajectory.start().getX() > trajectory.end().getX()
                && trajectory.start().getY() < trajectory.end().getY()) {
            // Adjust position if the ball was moving up and to the right
            this.center = new Point(collisionPoint.getX() + ONE_STEP, collisionPoint.getY() - ONE_STEP);

        } else if (trajectory.start().getX() < trajectory.end().getX()
                && trajectory.start().getY() > trajectory.end().getY()) {
            // Adjust position if the ball was moving down and to the left
            this.center = new Point(collisionPoint.getX() - ONE_STEP, collisionPoint.getY() + ONE_STEP);

        } else if (DoubleEquals.equals(trajectory.start().getX(), trajectory.end().getX())) {
            // Adjust position if the ball was moving vertically
            if (trajectory.start().getY() > trajectory.end().getY()) {
                this.center = new Point(collisionPoint.getX(), collisionPoint.getY() + ONE_STEP);
            }
            if (trajectory.start().getY() < trajectory.end().getY()) {
                this.center = new Point(collisionPoint.getX(), collisionPoint.getY() - ONE_STEP);
            }

        } else if (DoubleEquals.equals(trajectory.start().getY(), trajectory.end().getY())) {
            // Adjust position if the ball was moving horizontally
            if (trajectory.start().getX() > trajectory.end().getX()) {
                this.center = new Point(collisionPoint.getX() + ONE_STEP, collisionPoint.getY());
            }
            if (trajectory.start().getX() < trajectory.end().getX()) {
                this.center = new Point(collisionPoint.getX() - ONE_STEP, collisionPoint.getY());
            }

        } else {
            // Adjust position if the ball was moving diagonally
            this.center = new Point(collisionPoint.getX(), collisionPoint.getY());
        }
    }

    /**
     * Updates the ball's velocity based on a collision with another object.
     *
     * @param collideObjInfo The CollisionInfo object representing the collision.
     */
    private void updateVelocity(CollisionInfo collideObjInfo) {
        this.setVelocity(collideObjInfo.collisionObject().hit(this, collideObjInfo.collisionPoint(),
                this.getVelocity()));
    }

    /**
     * Sets game environment.
     *
     * @param environment the environments
     */
    public void setGameEnvironment(GameEnvironment environment) {
        this.environment = environment;
    }

    /**
     * This method is called whenever the game clock ticks.
     * It moves the ball one step according to its velocity.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * This method adds the ball to a given game.
     *
     * @param g the game to add the ball to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Remove from game.
     *
     * @param g the g
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}

