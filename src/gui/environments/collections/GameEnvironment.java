package gui.environments.collections;

import biuoop.DrawSurface;
import gui.environments.interfaces.Collidable;
import gui.environments.hitting.CollisionInfo;
import gui.graphics.Rectangle;
import gui.graphics.Point;
import gui.graphics.Line;


/**
 * The GameEnvironment class represents the environment of a game, which consists of a collection of collidable
 * objects. The class provides methods to add collidable objects to the environment, detect collisions with a line
 * trajectory,
 * and draw all collidable objects on a given DrawSurface.
 *
 * @author Guy Baruch ID 207707472 EX6
 */
public class GameEnvironment {
    // A list to hold all collidable objects in the environment.
    private java.util.List<Collidable> environment = new java.util.ArrayList<>();

    /**
     * Adds a given collidable object to the game environment.
     *
     * @param c the collidable object to be added to the environment.
     */
    public void addCollidable(Collidable c) {
        this.environment.add(c);
    }

    /**
     * Removes a given collidable object from the game environment.
     *
     * @param c the collidable object to be removed to the environment.
     */
    public void removeCollidable(Collidable c) {
        this.environment.remove(c);
    }

    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.

    /**
     * Check if an object moving along the given trajectory will collide with any of the collidable objects in this
     * environment. If there is a collision, return the CollisionInfo object that represents the closest collision
     * point to the starting point of the trajectory. If there are no collisions, return null.
     *
     * @param trajectory The trajectory of the moving object.
     * @return The CollisionInfo object that represents the closest collision point to the starting point of the
     * trajectory if there is a collision, or null if there are no collisions.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        // A list to hold all collision points that are detected.
        java.util.List<CollisionInfo> collisionInfoList = new java.util.ArrayList<>();

        // Iterate over all collidable objects in the environment.
        for (Collidable collidable : this.environment) {
            Rectangle rect = collidable.getCollisionRectangle();
            // Check if the trajectory intersects with the collidable object.
            if (trajectory.closestIntersectionToStartOfLine(rect) != null) {
                Point collidePoint =
                        trajectory.closestIntersectionToStartOfLine(rect);
                collisionInfoList.add(new CollisionInfo(collidePoint,
                        collidable));
            }
        }

        // If there are no collision points, return null.
        if (collisionInfoList.isEmpty()) {
            return null;
        }

        // Find the closest collision point to the starting point of the trajectory.
        double min = trajectory.length();
        CollisionInfo closestPointInfo = null;
        for (CollisionInfo collideInfoPoint : collisionInfoList) {
            double distance = collideInfoPoint.collisionPoint().distance(trajectory.start());

            // Check if the current collision point is closer than the previously found collision point.
            if (distance < min) {
                min = distance;
                closestPointInfo = collideInfoPoint;
            }
        }
        return closestPointInfo;
    }

    /**
     * Draw all the collidables in the environment on the given DrawSurface.
     *
     * @param d the DrawSurface to draw on.
     */
    public void drawAllOn(DrawSurface d) {
        for (Collidable collidable : environment) {
            collidable.drawOn(d);
        }
    }

}