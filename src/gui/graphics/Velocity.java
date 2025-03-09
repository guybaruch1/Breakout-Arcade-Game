package gui.graphics;

/**
 * Velocity specifies the change in position on the `x` and the `y` axes.
 *
 * @author Guy Baruch ID 207707472 EX6
 */
public class Velocity {
    // Fields
    private final double dx;
    private final double dy;

    /**
     * Instantiates a new Velocity.
     *
     * @param dx the dx vector
     * @param dy the dy vector
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }


    /**
     * get velocity from angel and speed.
     *
     * @param angle the angle
     * @param speed the speed
     * @return the velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double a = Math.toRadians(angle);
        double dx = speed * Math.cos(a);
        double dy = speed * Math.sin(a);
        return new Velocity(dx, dy);
    }


    /**
     * Gets dx vector.
     *
     * @return the dx vector
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Gets dy vector.
     *
     * @return the dy vector
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     *
     * @param p the point
     * @return the new point
     */
    public Point applyToPoint(Point p) {
        double x = p.getX();
        double y = p.getY();
        return new Point(x + this.getDx(), y + this.getDy());
    }
}