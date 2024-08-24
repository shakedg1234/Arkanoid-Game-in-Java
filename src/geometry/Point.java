// 211604343 Shaked Gitta
package geometry;

/**
 * The geometry.Point class represents a point in two-dimensional space.
 */
public class Point {
    private double x;
    private double y;

    /**
     * Creates a new geometry.Point with the specified x and y coordinates.
     *
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     */

    public Point(double x, double y) {
        this.x = x;
        this.y = y;

    }

    /**
     * Calculates the distance between this point and another point.
     *
     * @param other the other point to calculate the distance to
     * @return the distance between this point and the other point
     */
    public double distance(Point other) {
        double sum = Math.abs((this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y));
        double root = Math.sqrt(sum);
        return root;
    }

    /**
     * Compares this point to another point to determine if they are equal.
     *
     * @param other the other point to compare to
     * @return true if the points are equal, false otherwise
     */

    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        if (this == other) {
            return true;
        }
        if (this.x == other.getX() && this.y == other.y) {
            return true;
        }

        return Math.abs(this.x - other.x) < 1E-10 && Math.abs(this.y - other.y) <= 1E-10;
    }


    /**
     * Gets the x coordinate of this point.
     *
     * @return the x coordinate of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * Gets the y coordinate of this point.
     *
     * @return the y coordinate of this point
     */
    public double getY() {
        return this.y;

    }
}

