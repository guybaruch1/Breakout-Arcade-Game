package gui.environments.objects;

import biuoop.DrawSurface;
import gui.environments.interfaces.Collidable;
import gui.environments.interfaces.HitListener;
import gui.environments.interfaces.HitNotifier;
import gui.environments.interfaces.Sprite;
import gui.graphics.Rectangle;
import gui.graphics.Velocity;
import gui.graphics.Point;
import gui.animation.GameLevel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import numbers.DoubleEquals;

/**
 * The type Block.
 *
 * @author Guy Baruch ID 207707472 EX6
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private final Rectangle block;
    private java.awt.Color color;
    private List<HitListener> hitListeners = new ArrayList<>();

    /**
     * Instantiates a new Block.
     *
     * @param rect the rect
     */
    public Block(Rectangle rect) {
        this.block = rect;
    }

    /**
     * Sets color.
     *
     * @param color the color
     */
    public void setColor(java.awt.Color color) {
        this.color = color;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.block;
    }

    /**
     * Gets left x.
     *
     * @return the left x
     */
    public double getLeftX() {
        return this.block.getUpperLeft().getX();
    }

    /**
     * Gets right x.
     *
     * @return the right x
     */
    public double getRightX() {
        return this.block.getUpperRight().getX();
    }

    /**
     * Gets up y.
     *
     * @return the up y
     */
    public double getUpY() {
        return this.block.getUpperLeft().getY();
    }

    /**
     * Gets down y.
     *
     * @return the down y
     */
    public double getDownY() {
        return this.block.getLowerLeft().getY();
    }

    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Calculates the new velocity of the ball after a collision with a given point.
     * If the ball hits the frame, the velocity is changed to bounce off the frame.
     * If the ball hits a corner, the velocity is changed to bounce off the corner.
     *
     * @param collisionPoint  the point at which the ball collided with another object
     * @param currentVelocity the current velocity of the ball
     * @return the new velocity of the ball after the collision
     */
    @Override

    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // Get the x and y components of the current velocity
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        this.notifyHit(hitter);

        // If the ball hit a corner, change the velocity to bounce off the corner
        if ((DoubleEquals.equals(collisionPoint.getX(), this.getRightX())
                || (DoubleEquals.equals(collisionPoint.getX(), this.getLeftX())))
                && (DoubleEquals.equals(collisionPoint.getY(), this.getDownY())
                || DoubleEquals.equals(collisionPoint.getY(), this.getUpY()))) {
            return new Velocity(-dx, -dy);
        }

        // If the ball hit the frame (the X bound), change the velocity to bounce off the frame
        if (DoubleEquals.equals(collisionPoint.getX(), this.getRightX())
                || DoubleEquals.equals(collisionPoint.getX(), this.getLeftX())) {
            return new Velocity(-dx, dy);
            // If the ball hit the frame (the Y bound), change the velocity to bounce off the frame
        } else if (DoubleEquals.equals(collisionPoint.getY(), this.getDownY())
                || DoubleEquals.equals(collisionPoint.getY(), this.getUpY())) {
            return new Velocity(dx, -dy);
            // If the ball hit the paddle, change the velocity according to the paddle hit location
        } else {
            return currentVelocity;
        }
    }

    /**
     * The drawOn method draws the block on the given surface.
     *
     * @param surface the surface on which to draw the block
     */
    public void drawOn(DrawSurface surface) {
        // Set the color of the block and fill its rectangle on the surface
        surface.setColor(this.color);
        surface.fillRectangle((int) this.block.getUpperLeft().getX(),
                (int) this.block.getUpperLeft().getY(),
                (int) this.block.getWidth(), (int) this.block.getHeight());

        // Set the color of the block's border and draw its rectangle on the surface
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) this.block.getUpperLeft().getX(),
                (int) this.block.getUpperLeft().getY(),
                (int) this.block.getWidth(), (int) this.block.getHeight());
    }

    /**
     * The block doesn't move.
     */
    @Override
    public void timePassed() {
    }

    /**
     * The addToGame method adds the ball to the given game.
     *
     * @param g the game to which the ball will be added
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * The removeFromGame method remove the block from a given game.
     *
     * @param g the game to which the block will be removed
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
        g.removeCollidable(this);
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
