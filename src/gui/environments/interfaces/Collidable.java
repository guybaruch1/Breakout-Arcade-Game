package gui.environments.interfaces;

import biuoop.DrawSurface;
import gui.environments.objects.Ball;
import gui.graphics.Rectangle;
import gui.graphics.Velocity;
import gui.graphics.Point;

/**
 * The Collidable interface represents objects that can be collided with.
 *
 * @author Guy Baruch ID 207707472 EX6
 */
public interface Collidable {
    /**
     * Returns the "collision shape" of the object.
     *
     * @return the collision rectangle of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notifies the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     *
     * @param hitter          the hitter
     * @param collisionPoint  the point of collision.
     * @param currentVelocity the current velocity of the colliding object.
     * @return the new velocity expected after the hit.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * Draws the object on the given DrawSurface.
     *
     * @param d the DrawSurface to draw the object on.
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the object that a unit of time has passed.
     */
    void timePassed();
}