package gui.graphics;

import java.util.List;

import numbers.DoubleEquals;

/**
 * The type Line represent by two points.
 *
 * @author Guy Baruch ID 207707472 EX6
 */
public class Line {
    /**
     * The constant MIDDLE.
     */
    public static final int MIDDLE = 2;
    // Fields
    // start point
    private final Point start;

    // end point
    private final Point end;

    /**
     * Instantiates a new Line by two points.
     *
     * @param start the start point
     * @param end   the end point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Instantiates a new Line by 4 values of two points.
     *
     * @param x1 the x value of the start point
     * @param y1 the y value of the start point
     * @param x2 the x value of the end point
     * @param y2 the y value of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Return the length of the line.
     *
     * @return the length
     */
    public double length() {
        // Use the distance function from the Point class
        return this.start.distance(this.end);
    }

    /**
     * Returns the middle point of the line.
     *
     * @return the middle point
     */
    public Point middle() {
        return new Point((this.start.getX() + this.end.getX()) / MIDDLE, (this.start.getY() + this.end.getY())
                / MIDDLE);
    }

    /**
     * Returns the start point of the line.
     *
     * @return the start point
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the end point of the line.
     *
     * @return the end point
     */
    public Point end() {
        return this.end;
    }

    /**
     * Returns the slope of the line.
     *
     * @return the slope
     */
    public double slope() {
        return ((this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX()));
    }

    /**
     * return true if the slope of two lines are equal, false otherwise.
     *
     * @param other the other
     * @return boolean boolean
     */
    public boolean sameSlope(Line other) {
        return DoubleEquals.equals(this.slope(), other.slope());
    }

    /**
     * Return the Y value of the line's intersection point with the Y axe.
     *
     * @return the Y value of the line's intersection point with the Y axe
     */
    public double interY() {
        return (this.start.getY() - this.slope() * this.start.getX());
    }

    /**
     * This method checks if a given value of x is in between two ranges defined by maxX1, minX1 and maxX2, minX2.
     *
     * @param x     The value of x to check.
     * @param maxX1 The maximum value of the first range.
     * @param minX1 The minimum value of the first range.
     * @param maxX2 The maximum value of the second range.
     * @param minX2 The minimum value of the second range.
     * @return true if x is in between the two ranges, false otherwise.
     */
    public boolean inBetweenX(double x, double maxX1, double minX1, double maxX2, double minX2) {
        // Check if x is less than or equal to maxX1 and greater than or equal to minX1
        // and also less than or equal to maxX2 and greater than or equal to minX2
        return (x < maxX1 || DoubleEquals.equals(x, maxX1)) && (x > minX1 || DoubleEquals.equals(x, minX1))
                && (x <= maxX2 || DoubleEquals.equals(x, maxX2)) && (x >= minX2 || DoubleEquals.equals(x, minX2));
    }

    /**
     * This method checks if a given value of y is in between two ranges defined by maxY1, minY1 and maxY2, minY2.
     *
     * @param y     The value of y to check.
     * @param maxY1 The maximum value of the first range.
     * @param minY1 The minimum value of the first range.
     * @param maxY2 The maximum value of the second range.
     * @param minY2 The minimum value of the second range.
     * @return true if y is in between the two ranges, false otherwise.
     */
    public boolean inBetweenY(double y, double maxY1, double minY1, double maxY2, double minY2) {
        // Check if y is less than or equal to maxY1 and greater than or equal to minY1
        // and also less than or equal to maxY2 and greater than or equal to minY2
        return (y <= maxY1 || DoubleEquals.equals(y, maxY1)) && (y >= minY1 || DoubleEquals.equals(y, minY1))
                && (y <= maxY2 || DoubleEquals.equals(y, maxY2)) && (y >= minY2 || DoubleEquals.equals(y, minY2));
    }

    /**
     * Returns true if the lines intersect, false otherwise.
     *
     * @param other the other line
     * @return boolean boolean
     */
    @SuppressWarnings("checkstyle:SimplifyBooleanReturn")
    public boolean isIntersecting(Line other) {

        // line slope
        double m1 = this.slope();
        // line Y value of the intersection point with the Y axe
        double b1 = this.interY();
        // other line slope
        double m2 = other.slope();
        // other line Y value of the intersection point with the Y axe
        double b2 = other.interY();

        // minimum from the x values of the start and end points of the line
        double minX1 = Math.min(this.start.getX(), this.end.getX());
        // maximum from the x values of the start and end points of the line
        double maxX1 = Math.max(this.start.getX(), this.end.getX());
        // minimum from the x values of the start and end points of other line
        double minX2 = Math.min(other.start.getX(), other.end.getX());
        // maximum from the x values of the start and end points of other line
        double maxX2 = Math.max(other.start.getX(), other.end.getX());
        // minimum from the y values of the start and end points of the line
        double minY1 = Math.min(this.start.getY(), this.end.getY());
        // maximum from the y values of the start and end points of the line
        double maxY1 = Math.max(this.start.getY(), this.end.getY());
        // minimum from the y values of the start and end points of other line
        double minY2 = Math.min(other.start.getY(), other.end.getY());
        // maximum from the x values of the start and end points of other line
        double maxY2 = Math.max(other.start.getY(), other.end.getY());

        // if the lines are equal return null
        if (this.equals(other)) {
            return true;
        }


        // if the two lines slope are infinity
        if (DoubleEquals.equals(this.end.getX(), this.start.getX())
                && DoubleEquals.equals(other.end.getX(), other.start.getX())) {
            double x1 = this.start.getX();
            double x2 = other.start.getX();
            if (DoubleEquals.equals(x1, x2)) {
                if (DoubleEquals.equals(minY1, maxY2) || DoubleEquals.equals(minY2, maxY1)) {
                    return true;
                }
            } else {
                return false;
            }
        }

        // if the slope of this.line is infinity
        if (DoubleEquals.equals(this.end.getX(), this.start.getX())) {
            // then if the lines intersects so this is the X and Y values
            double x = this.start.getX();
            double y = other.slope() * x + other.interY();
            // Check if the two lines intersect
            return inBetweenX(x, maxX1, minX1, maxX2, minX2) && inBetweenY(y, maxY1, minY1, maxY2, minY2);
        }

        // if the slope of other.line is infinity
        if (DoubleEquals.equals(other.end.getX(), other.start.getX())) {
            // then if the lines intersects so this is the X and Y values
            double x = other.start.getX();
            double y = this.slope() * x + this.interY();
            // Check if the two lines intersect
            return inBetweenX(x, maxX1, minX1, maxX2, minX2) && inBetweenY(y, maxY1, minY1, maxY2, minY2);
        }

        // If the lines have the same slope and y-intercept,
        // they are parallel and can only intersect if they overlap
        if (DoubleEquals.equals(m1, m2) && DoubleEquals.equals(b1, b2)) {
            if (DoubleEquals.equals(m1, 0)) {
                return DoubleEquals.equals(minX1, maxX2) || DoubleEquals.equals(minX2, maxX1);
            }
            // Check if the two lines overlap or intersect
            return minX1 < maxX2 || minX2 < maxX1 || DoubleEquals.equals(minX1, maxX2)
                    || DoubleEquals.equals(minX2, maxX2);
        }

        // if the end line touches the start of the other line
        if (this.start().equals(other.end()) || this.end().equals(other.start())) {
            return true;
        }

        // the values of the intersection point if there is an intersection
        double x = (other.interY() - this.interY()) / (this.slope() - other.slope());
        double y = this.slope() * x + this.interY();

        // calculation if the lines are intersect
        return inBetweenX(x, maxX1, minX1, maxX2, minX2) && inBetweenY(y, maxY1, minY1, maxY2, minY2);
    }

    /**
     * Returns the intersection point if the lines intersect, and null otherwise.
     *
     * @param other the other line
     * @return the intersection point
     */
    public Point intersectionWith(Line other) {

        // line slope
        double m1 = this.slope();
        // line Y value of the intersection point with the Y axe
        double b1 = this.interY();
        // other line slope
        double m2 = other.slope();
        // other line Y value of the intersection point with the Y axe
        double b2 = other.interY();

        // minimum from the x values of the start and end points of the line
        double minX1 = Math.min(this.start.getX(), this.end.getX());
        // maximum from the x values of the start and end points of the line
        double maxX1 = Math.max(this.start.getX(), this.end.getX());
        // minimum from the x values of the start and end points of other line
        double minX2 = Math.min(other.start.getX(), other.end.getX());
        // maximum from the x values of the start and end points of other line
        double maxX2 = Math.max(other.start.getX(), other.end.getX());
        // minimum from the y values of the start and end points of the line
        double minY1 = Math.min(this.start.getY(), this.end.getY());
        // maximum from the y values of the start and end points of the line
        double maxY1 = Math.max(this.start.getY(), this.end.getY());
        // minimum from the y values of the start and end points of other line
        double minY2 = Math.min(other.start.getY(), other.end.getY());
        // maximum from the x values of the start and end points of other line
        double maxY2 = Math.max(other.start.getY(), other.end.getY());
        // check if the lines are intersect with the isIntersecting function
        if (!isIntersecting(other)) {
            return null;
        }
        // if the lines are equal return null
        if (this.equals(other)) {
            return null;
        }
        // if the lines are parallel, and they have intersection point
        // in the start or the end point
        if (DoubleEquals.equals(m1, m2) && this.start().equals(other.end())) {
            return this.start();
        }
        if (DoubleEquals.equals(m1, m2) && this.end().equals(other.start())) {
            return this.end();
        }
        if (DoubleEquals.equals(m1, m2)) {
            return null;
        }
        // if the two lines slope are infinity
        if (DoubleEquals.equals(this.end.getX(), this.start.getX())
                && DoubleEquals.equals(other.end.getX(), other.start.getX())) {
            double x1 = this.start.getX();
            double x2 = other.start.getX();
            if (DoubleEquals.equals(x1, x2)) {
                if (DoubleEquals.equals(minY1, maxY2)) {
                    return new Point(x1, minY1);
                }
                if (DoubleEquals.equals(minY2, maxY1)) {
                    return new Point(x1, minY2);
                }
                if (minY1 < maxY2 || minY2 < maxY1) {
                    return null;
                }
            } else {
                return null;
            }
        }
        // if the slope of this.line is infinity
        if (DoubleEquals.equals(this.end.getX(), this.start.getX())) {
            // then if the lines intersects so this is the X and Y values
            double x = this.start.getX();
            double y = other.slope() * x + other.interY();
            // Check if the two lines intersect
            if (inBetweenX(x, maxX1, minX1, maxX2, minX2) && inBetweenY(y, maxY1, minY1, maxY2, minY2)) {
                // return the intersection point
                return new Point(x, y);
            }
        }
        // if the slope of other.line is infinity
        if (DoubleEquals.equals(other.end.getX(), other.start.getX())) {
            // then if the lines intersects so this is the X and Y values
            double x = other.start.getX();
            double y = this.slope() * x + this.interY();
            // Check if the two lines intersect
            if (inBetweenX(x, maxX1, minX1, maxX2, minX2) && inBetweenY(y, maxY1, minY1, maxY2, minY2)) {
                // return the intersection point
                return new Point(x, y);
            }
        }
        // the values of the intersection point
        double x = (other.interY() - this.interY()) / (this.slope() - other.slope());
        double y = this.slope() * x + this.interY();

        // return the intersection point
        return new Point(x, y);

    }

    /**
     * return true is the lines are equal, false otherwise.
     *
     * @param other the other point
     * @return boolean boolean
     */
    public boolean equals(Line other) {
        return (this.end().equals(other.end()) && this.start().equals(other.start()))
                || (this.end().equals(other.start()) && this.start().equals(other.end()));
    }

    /**
     * If this line does not intersect with the rectangle, return null. Otherwise, return the closest intersection
     * point to the start of the line.
     *
     * @param rect The rectangle to find the intersection points with.
     * @return The closest intersection point to the start of the line, or null if there are no
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        // Find the intersection points between the line and the rectangle
        List<Point> intersectionPoints = rect.intersectionPoints(new Line(this.start, this.end));

        // If there are no intersection points, return null
        if (intersectionPoints.isEmpty()) {
            return null;
        }

        // Find the closest intersection point to the start of the line
        Point closestPoint = null;
        double min = this.length();
        for (Point interPoint : intersectionPoints) {
            double distance = interPoint.distance(this.start);
            if (distance < min || DoubleEquals.equals(distance, min)) {
                min = distance;
                closestPoint = interPoint;
            }
        }
        return closestPoint;
    }

}