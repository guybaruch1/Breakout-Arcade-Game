package gui.graphics;

import numbers.DoubleEquals;

/**
 * The type Point.
 *
 * @author Guy Baruch ID 207707472 EX6
 */
public class Point {

    // Fields
    // the x value of the point
    private final double x;

    // the y value of the point
    private final double y;

    /**
     * Instantiates a new Point.
     *
     * @param x the x value of the point
     * @param y the y value of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * return the distance of this point to the other point.
     *
     * @param other the other point
     * @return the distance
     */
    public double distance(Point other) {
        return Math.sqrt(((this.x - other.getX()) * (this.x - other.getX()))
                + ((this.y - other.getY()) * (this.y - other.getY())));
    }

    /**
     * return true is the points are equal, false otherwise.
     *
     * @param other the other point
     * @return boolean boolean
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        return (DoubleEquals.equals(this.getX(), other.getX()) && DoubleEquals.equals(this.getY(), other.getY()));
    }

    /**
     * Return the x value of this point.
     *
     * @return the x value
     */
    public double getX() {
        return this.x;
    }

    /**
     * Return the y values of this point.
     *
     * @return the y value
     */
    public double getY() {
        return this.y;
    }
}