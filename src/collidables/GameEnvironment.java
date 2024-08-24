// 211604343 Shaked Gitta

package collidables;


import geometry.Point;
import geometry.Line;

import java.util.ArrayList;


/**
 * The GameEnvironment class represents the environment in which the game operates.
 * It contains a list of all the collidable objects in the game.
 */
public class GameEnvironment {

    private ArrayList<Collidable> collidables;

    /**
     * Constructs a GameEnvironment object with the given collection of collidables.
     *
     * @param collidables a collection of collidable objects
     */

    public GameEnvironment(ArrayList<Collidable> collidables) {
        this.collidables = collidables;
    }

    /**
     * Constructs a collidables.GameEnvironment object with an empty collection of collidables.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * Adds the given collidable object to the environment.
     *
     * @param c the collidable object to add
     */
    // add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * Returns the collection of collidable objects in the environment.
     *
     * @return the collection of collidable objects
     */


    public ArrayList<Collidable> getCollidables() {
        return collidables;
    }

    /**
     * Returns the number of collidable objects in the environment.
     *
     * @return the number of collidable objects
     */

    public int getSize() {
        return this.collidables.size();
    }

    /**
     * Returns the collidable object at the given index in the collection.
     *
     * @param x the index of the collidable object to retrieve
     * @return the collidable object at the given index
     */

    public Collidable getCollidable(int x) {
        return this.collidables.get(x);
    }

    /**
     * Detects the closest collision between any of the collidable objects in the environment and a given trajectory.
     * Assumes an object moving from line.start() to line.end().
     * If no collision occurs, returns null. Else, returns a collidables.CollisionInfo object describing
     * the closest collision.
     *
     * @param trajectory the trajectory to check for collisions
     * @return a collidables.CollisionInfo object describing the closest collision, or null if no collision occurs
     */


    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    public CollisionInfo getClosestCollision(Line trajectory) {
        // collidables.CollisionInfo c1 = new collidables.CollisionInfo();
        Point p1 = null;
        Point check = null;
        double closestDistance = -1;
        int num = 0;
        // Check each collidable object in the game environment for a collision with the trajectory.
        for (int i = 0; i < collidables.size(); i++) {
            // Get the point of intersection between the trajectory and the current collidable object.
            p1 = trajectory.closestIntersectionToStartOfLine(collidables.get(i).getCollisionRectangle());
            // If a point of intersection was found and it is closer to the start of the trajectory than any previous
            // intersection, update the closest collision information.
            if (p1 != null && (closestDistance == -1 || p1.distance(trajectory.start()) < closestDistance)) {
                check = p1;
                closestDistance = check.distance(trajectory.start());
                num = i;
            }
        }
        // If no collision was found, return null.
        if (closestDistance == -1 || check == null) {
            return null;
        }
        // Otherwise, create and return a collidables.CollisionInfo object with the closest collision information.
        CollisionInfo c1 = new CollisionInfo(check, collidables.get(num));
        return c1;

    }

    /**
     * Checks if the given point is inside any of the collidables' collision rectangle.
     *
     * @param check the point to check
     * @param r     the radius of the point (used for collision detection with blocks)
     * @return true if the point is inside a collision rectangle, false otherwise
     */

    public boolean checkPointInAll(Point check, int r) {
        for (int i = 0; i < collidables.size() - 1; i++) {
            if (collidables.get(i).getCollisionRectangle().checkPointInside(check, r)) {
                return true;
            }
        }
        return false;
    }

}