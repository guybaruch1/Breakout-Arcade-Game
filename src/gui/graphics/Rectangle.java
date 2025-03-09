package gui.graphics;

import java.util.ArrayList;
import java.util.List;

/**
 * The Rectangle class represents a rectangle defined by its upper left point,
 * width and height.
 *
 * @author Guy Baruch ID 207707472 EX6
 */
public class Rectangle {

    private Point upperLeft;
    private final double width;
    private final double height;


    /**
     * Create a new rectangle with location and width/height.
     *
     * @param upperLeft the upper left point of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Returns the top line of the rectangle.
     *
     * @return the top line of the rectangle
     */
    public Line getUp() {
        Point upperLeft = this.upperLeft;
        Point upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        return new Line(upperLeft, upperRight);
    }

    /**
     * Returns the bottom line of the rectangle.
     *
     * @return the bottom line of the rectangle
     */
    public Line getDown() {
        Point lowerRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
        Point lowerLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        return new Line(lowerLeft, lowerRight);
    }

    /**
     * Returns the left line of the rectangle.
     *
     * @return the left line of the rectangle
     */
    public Line getLeft() {
        Point upperLeft = this.upperLeft;
        Point lowerLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        return new Line(upperLeft, lowerLeft);
    }

    /**
     * Returns the right line of the rectangle.
     *
     * @return the right line of the rectangle
     */
    public Line getRight() {
        Point upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Point lowerRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
        return new Line(upperRight, lowerRight);
    }

    /**
     * Return a (possibly empty) List of intersection points with the specified line.
     *
     * @param line The line to check for intersection points with
     * @return A list of intersection points with the specified line
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> intersectionPoints = new ArrayList<>();

        // Check for intersection with each side of the rectangle
        if (line.isIntersecting(this.getUp())) {
            intersectionPoints.add(line.intersectionWith(this.getUp()));
        }
        if (line.isIntersecting(this.getDown())) {
            intersectionPoints.add(line.intersectionWith(this.getDown()));
        }
        if (line.isIntersecting(this.getLeft())) {
            intersectionPoints.add(line.intersectionWith(this.getLeft()));
        }
        if (line.isIntersecting(this.getRight())) {
            intersectionPoints.add(line.intersectionWith(this.getRight()));
        }
        return intersectionPoints;
    }

    /**
     * Returns the width of the rectangle.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Returns the height of the rectangle.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return the upper-left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Returns the upper-right point of the rectangle.
     *
     * @return the upper-right point of the rectangle
     */
    public Point getUpperRight() {
        return new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
    }

    /**
     * Returns the lower-left point of the rectangle.
     *
     * @return the lower-left point of the rectangle
     */
    public Point getLowerLeft() {
        return new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
    }

    /**
     * Returns the lower-right point of the rectangle.
     *
     * @return the lower-right point of the rectangle
     */
    public Point getLowerRight() {
        return new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
    }
}