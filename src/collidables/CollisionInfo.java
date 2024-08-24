// 211604343 Shaked Gitta
package collidables;

import geometry.Point;

/**

 A class to hold information about a collision that occurred between a ball and a collidable object.
 */
public class CollisionInfo {
    private Point point;
    private Collidable collidable;
    /**

     Constructor.
     @param p the point at which the collision occurred.
     @param collidable the collidable object involved in the collision.
     */

    public CollisionInfo(Point p, Collidable collidable) {
        this.point = p;
        this.collidable = collidable;
    }
    /**

     Returns the point at which the collision occurred.
     @return the collision point.
     */


    // the point at which the collision occurs.
    public Point collisionPoint() {
        return point;
    }
    /**

     Returns the collidable object involved in the collision.
     @return the collidable object.
     */

    // the collidable object involved in the collision.
    public Collidable collisionObject() {
        return collidable;
    }
}