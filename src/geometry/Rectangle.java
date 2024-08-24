// 211604343 Shaked Gitta
package geometry;
import java.util.ArrayList;

/**
 * The Rectangle class represents a rectangle in 2D space.
 */
public class Rectangle {
    private static final double THRESHOLD = 0.001;
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Creates a new rectangle with the specified location and dimensions.
     *
     * @param upperLeft the upper-left point of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.height = height;
        this.width = width;

    }

    /**
     * Sets the upper-left point of the rectangle.
     *
     * @param upperLeft1 the new upper-left point of the rectangle
     */

    public void setUpperLeft(Point upperLeft1) {
        this.upperLeft = upperLeft1;
    }

    /**
     * Returns a (possibly empty) list of intersection points between
     * the rectangle and the specified line.
     *
     * @param line the line to check for intersections with the rectangle
     * @return a list of intersection points between the rectangle and the line
     */

    // Return a (possibly empty) List of intersection points
    // with the specified line.
    public java.util.List<Point> intersectionPoints(Line line) {
        // Create Lines that represent the sides of the geometry.Rectangle
        Line top = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX() + this.width, upperLeft.getY());
        Line right = new Line(upperLeft.getX() + this.width, upperLeft.getY(), upperLeft.getX()
                + this.width, upperLeft.getY() + this.height);
        Line bottom = new Line(upperLeft.getX(), upperLeft.getY() + this.height, upperLeft.getX()
                + width, upperLeft.getY() + this.height);
        Line left = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX(), upperLeft.getY() + this.height);
        // Create an empty list to hold the intersection points
        java.util.List<Point> intersectionPoints = new ArrayList<Point>();
        // Check for intersections with each of the four sides of the geometry.Rectangle

        if (line.isIntersecting(top)) {
            intersectionPoints.add(line.intersectionWith(top));
        }
        if (line.isIntersecting(right)) {
            intersectionPoints.add(line.intersectionWith(right));
        }
        if (line.isIntersecting(bottom)) {
            intersectionPoints.add(line.intersectionWith(bottom));
        }
        if (line.isIntersecting(left)) {
            intersectionPoints.add(line.intersectionWith(left));
        }
        // Return the list of intersection points
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
        return (new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY()));
    }

    /**
     * Returns the bottom-left point of the rectangle.
     *
     * @return the bottom-left point of the rectangle
     */
    public Point getBottomLeft() {
        return new Point(this.upperLeft.getX(), this.upperLeft.getY() + height);
    }

    /**
     * Returns the bottom-right point of the rectangle.
     *
     * @return the bottom-right point of the rectangle
     */

    public Point getBottomRight() {
        return new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
    }

    public Boolean checkPointInside(Point check, int r) {
        double xi = check.getX();
        double yi = check.getY();
        double x1 = this.upperLeft.getX();
        double y1 = this.upperLeft.getY();
        double x2 = getBottomLeft().getX();
        double y2 = getBottomLeft().getY();
        double x3 = getUpperRight().getX();
        double y3 = getUpperRight().getY();
        double x4 = getBottomRight().getX();
        double y4 = getBottomRight().getY();
        double halfR = r / 2;

        return  (xi >= Math.min(x1, x3) - halfR && xi <= Math.max(x1, x3) + halfR
                && yi >= Math.min(y1, y2) - halfR && yi <= Math.max(y1, y2) + halfR);

    }


}