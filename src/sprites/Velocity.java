// 211604343 Shaked Gitta
package sprites;
import geometry.Point;

/**
 * A class that represents a velocity in 2D space, specifying the change in position on the `x` and the `y` axes.
 */
public class Velocity {

    private double x;
    private double y;

    /**
     * Constructs a new Velocity object with the specified change in position on the `x` and `y` axes.
     *
     * @param dx the change in position on the `x` axis
     * @param dy the change in position on the `y` axis
     */
    public Velocity(double dx, double dy) {
        this.x = dx;
        this.y = dy;
    }

    /**
     * Constructs a new Velocity object with no movement.
     */
    public Velocity() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Gets the change in position on the `x` axis.
     *
     * @return the change in position on the `x` axis
     */

    public double getX() {
        return this.x;
    }

    /**
     * Gets the change in position on the `y` axis.
     *
     * @return the change in position on the `y` axis
     */

    public double getY() {
        return y;
    }

    /**
     * Sets the x coordinate of the point.
     *
     * @param dx the new x coordinate value
     */

    public void setX(double dx) {
        this.x = dx;
    }

    /**
     * Sets the y coordinate of the point.
     *
     * @param dy the new y coordinate value
     */
    public void setY(double dy) {
        this.y = dy;
    }

    /**
     * Takes a point with position (x,y) and returns a new point with position (x+dx, y+dy).
     *
     * @param p the point to which the velocity will be applied
     * @return a new point with position (x+dx, y+dy)
     */

    public Point applyToPoint(Point p) {
        Point newp = new Point((p.getX() + this.x), (p.getY() + this.y));
        return newp;
    }

    /**
     * Creates a new Velocity object from an angle and a speed.
     *
     * @param angle the angle in radians
     * @param speed the speed of the object
     * @return a new Sprites.Velocity object
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radians = Math.toRadians(angle);
        double dx = Math.sin(radians) * speed;
        double dy = Math.cos(radians) * speed;
        return new Velocity(dx, dy);
    }
}