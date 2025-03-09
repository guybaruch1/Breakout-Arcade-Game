package gui.environments.hitting;

import gui.environments.interfaces.Collidable;
import gui.graphics.Point;

/**
 * CollisionInfo class represents information about a collision between a ball and a collidable object.
 *
 * @author Guy Baruch ID 207707472 EX6
 */
public class CollisionInfo {
    private final Point collisionPoint; // The point where the collision occurs
    private final Collidable collisionObject; // The collidable object involved in the collision

    /**
     * Constructor for CollisionInfo.
     *
     * @param collisionPoint  The point where the collision occurs
     * @param collisionObject The collidable object involved in the collision
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * Returns the point at which the collision occurs.
     *
     * @return The point where the collision occurs
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Returns the collidable object involved in the collision.
     *
     * @return The collidable object involved in the collision
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}