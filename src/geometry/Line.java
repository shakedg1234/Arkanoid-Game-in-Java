// 211604343 Shaked Gitta
package geometry;


/**
 * Represents a line segment in a two-dimensional plane, defined by two points - start and end.
 */
public class Line {
    private static final double THRESHOLD = 0.0001;
    private Point start;
    private Point end;

    /**
     * Constructs a Line object with given start and end points.
     *
     * @param start the start point of the line
     * @param end   the end point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;

    }

    /**
     * Constructs a Line object with given coordinates of start and end points.
     *
     * @param x1 the x-coordinate of the start point
     * @param y1 the y-coordinate of the start point
     * @param x2 the x-coordinate of the end point
     * @param y2 the y-coordinate of the end point
     */

    public Line(double x1, double y1, double x2, double y2) {
        Point st = new Point(x1, y1);
        Point end = new Point(x2, y2);
        this.start = st;
        this.end = end;
    }

    /**
     * Returns the length of the line.
     *
     * @return the length of the line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Returns the middle point of the line.
     *
     * @return the middle point of the line
     */
    public Point middle() {
        Point mid = new Point((this.end.getX() + this.start.getX()) / 2, (this.end.getY() + this.start.getY()) / 2);

        return mid;
    }

    /**
     * Returns the start point of the line.
     *
     * @return the start point of the line
     */

    public Point start() {
        return this.start;
    }


    /**
     * Returns the end point of the line.
     *
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * Returns true if the lines intersect, false otherwise.
     *
     * @param other the other line to check for intersection
     * @return true if the lines intersect, false otherwise
     */

    // Returns true if the lines intersect, false otherwise
    public boolean isIntersecting(Line other) {
        if (this.equals(other)) {
            return true;
        }
        if (this.checkPartsLines(other)) {
            return true;
        }
        if (this.intersectionWith(other) != null) {
            return true;
        }
        return false;



    }

    /**
     * Checks whether this line intersects with the given point.
     *
     * @param p1 the point to check intersection with
     * @return true if the line intersects with the given point, false otherwise
     */

    public boolean intersectionWithPoint(Point p1) {
        double x1 = this.start.getX();
        double x2 = this.end.getX();
        double y1 = this.start.getY();
        double y2 = this.end.getY();
        double xi = p1.getX();
        double yi = p1.getY();
        double dist = Math.abs((y2 - y1) * xi - (x2 - x1) * yi + x2 * y1 - y2 * x1)
                / Math.sqrt(Math.pow(y2 - y1, 2) + Math.pow(x2 - x1, 2));

        if (dist <= THRESHOLD) {
            double minX = Math.min(x1, x2) - THRESHOLD;
            double maxX = Math.max(x1, x2) + THRESHOLD;
            double minY = Math.min(y1, y2) - THRESHOLD;
            double maxY = Math.max(y1, y2) + THRESHOLD;
            if (xi >= minX && xi <= maxX && yi >= minY && yi <= maxY) {
                if (p1.equals(this.start) || p1.equals(this.end)) {
                    return true;
                }
                return true;
            }

        }

        return false;
    }

    /**
     * Checks if this Line intersects with the given Line at any of its end points.
     *
     * @param other the Line to check for intersection
     * @return true if there is an intersection, false otherwise
     */
    public Boolean checkPartsLines(Line other) {
        return (this.intersectionWithPoint(other.start) || this.intersectionWithPoint(other.end));
    }

    /**
     * Returns the intersection point if the lines intersect, and null otherwise.
     *
     * @param other the other line to check for intersection
     * @return the intersection point if the lines intersect, and null otherwise
     */

    public Point intersectionWith(Line other) {
        // Extract coordinates of the start and end points of both lines.

        double x1 = this.start.getX(), y1 = this.start.getY(), x2 = this.end.getX(), y2 = this.end.getY(),
                x3 = other.start.getX(), y3 = other.start.getY(), x4 = other.end.getX(), y4 = other.end.getY();
        // Calculate the determinant of the matrix representing the lines
        double d = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
        // Check for special cases where lines share a common point
        if (this.start.equals(other.start)) {
            return this.start;
        }
        if (this.start.equals(other.end)) {
            return this.start;
        }
        if (this.end.equals(other.start)) {
            return this.end;
        }
        if (this.end.equals(other.end)) {
            return this.end;
        }
        // Check if the lines are parallel

        if (d == 0) {
            return null;
        }
        // Calculate the intersection point

        double xi = ((x3 - x4) * (x1 * y2 - y1 * x2) - (x1 - x2) * (x3 * y4 - y3 * x4)) / d;
        double yi = ((y3 - y4) * (x1 * y2 - y1 * x2) - (y1 - y2) * (x3 * y4 - y3 * x4)) / d;
        // Check if the intersection point is within the bounds of both lines

        if (xi >= Math.min(x1, x2) - THRESHOLD && xi <= Math.max(x1, x2) + THRESHOLD && xi >= Math.min(x3, x4)
                - THRESHOLD && xi <= Math.max(x3, x4) + THRESHOLD
                && yi >= Math.min(y1, y2) - THRESHOLD && yi <= Math.max(y1, y2) + THRESHOLD && yi >= Math.min(y3, y4)
                - THRESHOLD && yi <= Math.max(y3, y4) + THRESHOLD) {
            return new Point(xi, yi);
        } else {
            return null;
        }
    }


    /**
     * Calculates the distance between this line segment and the given point.
     *
     * @param p the point to calculate the distance to
     * @return the distance between this line segment and the given point
     */
    public double distance(Point p) {
        double numerator = Math.abs((end.getY() - start.getY()) * p.getX() - (end.getX() - start.getX()) * p.getY()
                + end.getX() * start.getY() - end.getY() * start.getX());
        double denominator = Math.sqrt(Math.pow(end.getY() - start.getY(), 2) + Math.pow(end.getX() - start.getX(), 2));
        return numerator / denominator;
    }


    /**
     * Checks whether this Line object is equal to another Line object.
     *
     * @param other the other Line object to compare with
     * @return true if this Line object is equal to the other Line object, false otherwise
     */
    public boolean equals(Line other) {
        if (this.start.equals(other.start) && this.end.equals(other.end)) {
            return true;
        }
        if (this.end.equals(other.start) && this.start.equals(other.end)) {
            return true;
        }
        return false;
    }


    /**
     * Finds the closest intersection point between a given rectangle and the line represented by this Line object,
     * to the starting point of the line.
     *
     * @param rect the rectangle to check for intersection points with the line.
     * @return the closest intersection point to the starting point of the line, or null if there are
     * no intersection points.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        // Get the list of intersection points between the rectangle and the line
        java.util.List<Point> p = rect.intersectionPoints(this);
        // If there are no intersection points, return null
        if (p.isEmpty()) {
            return null;
        } else {
            // Initialize the closest intersection point and distance to the first point in the list
            Point closestIntersectionPoint = p.get(0);
            double closestDistance = start.distance(closestIntersectionPoint);
            // Iterate over the remaining intersection points and update closestIntersectionPoint
            // and closestDistance if necessary
            for (int i = 1; i < p.size(); i++) {
                Point intersectionPoint = p.get(i);
                double distance = start.distance(intersectionPoint);
                if (distance < closestDistance) {
                    closestIntersectionPoint = intersectionPoint;
                    closestDistance = distance;
                }
            }
            return closestIntersectionPoint;


        }


    }


}


