// 211604343 Shaked Gitta
package sprites;

import biuoop.DrawSurface;
import collidables.Collidable;
import collidables.CollisionInfo;
import collidables.GameEnvironment;
import games.GameLevel;
import geometry.Point;
import geometry.Line;
import geometry.Rectangle;

/**
 * The Ball class represents a ball object with a center point, radius, color, and velocity.
 */

public class Ball implements Sprite {

    private static final double THRESHOLD = 0.001;
    private int raduis;
    private Point center;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment game;


    /**
     * Constructor that creates a new Ball object with the given x and y coordinates, radius, and color.
     *
     * @param x     the x coordinate of the center of the ball
     * @param y     the y coordinate of the center of the ball
     * @param r     the radius of the ball
     * @param color the color of the ball
     * @param game  the GameEnvironment in which the ball operates
     */
    public Ball(int x, int y, int r, java.awt.Color color, GameEnvironment game) {
        Point c = new Point(x, y);
        this.center = c;
        this.raduis = r;
        this.color = color;
        this.velocity = new Velocity();
        this.game = game;
    }

    /**
     * Constructor that creates a new Ball object with the given center point, radius, and color.
     *
     * @param center the center point of the ball
     * @param r      the radius of the ball
     * @param color  the color of the ball
     * @param game   the GameEnvironment in which the ball operates
     */

    public Ball(Point center, int r, java.awt.Color color, GameEnvironment game) {
        this.center = center;
        this.raduis = r;
        this.color = color;
        this.velocity = new Velocity();
        this.game = game;
    }

    /**
     * Returns the x coordinate of the center of the ball.
     *
     * @return the x coordinate of the center of the ball
     */

    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Returns the y coordinate of the center of the ball.
     *
     * @return the y coordinate of the center of the ball
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Returns the radius of the ball.
     *
     * @return the radius of the ball
     */

    public int getSize() {
        return this.raduis;
    }

    /**
     * Returns the color of the ball.
     *
     * @return the color of the ball
     */

    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Draws the ball on the given DrawSurface.
     *
     * @param surface the DrawSurface on which to draw the ball
     */

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.raduis);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.raduis);
    }

    /**
     * Sets the velocity of the ball to the given GameEnvironment.Velocity object.
     *
     * @param v the GameEnvironment.Velocity object to set as the velocity of the ball
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets the velocity of the ball to the given x and y values.
     *
     * @param dx the x value of the velocity
     * @param dy the y value of the velocity
     */
    public void setVelocity(double dx, double dy) {
        Velocity velocity1 = new Velocity(dx, dy);
        this.velocity = velocity1;
    }

    /**
     * Returns the velocity of the ball.
     *
     * @return the velocity of the ball
     */

    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Checks for collisions with two borders and updates the ball's velocity accordingly.
     *
     * @param collision  the point of collision between the ball and an object.
     * @param newv       the new velocity of the ball.
     * @param collidable the object that the ball collided with.
     */


    public void checkTwoBorders(Point collision, Velocity newv, Collidable collidable) {
        Point check = this.getVelocity().applyToPoint(this.center);
        //check if the ball is in a block.
        if (game.checkPointInAll(check, this.raduis)) {
            this.velocity = new Velocity(-this.velocity.getX(), -this.velocity.getY());
            return;
        }
        // checks if the collision point is on the border of another collidable object
        for (int i = 0; i < this.game.getSize(); i++) {
            Rectangle rect = this.game.getCollidable(i).getCollisionRectangle();
            if (rect == collidable.getCollisionRectangle()) {
                continue;
            } else if (collision.equals(rect.getUpperLeft())
                    || collision.equals(rect.getBottomLeft())
                    || collision.equals(rect.getBottomRight())
                    || collision.equals(rect.getUpperRight())) {
                // update the velocity based on the collision angle
                if (newv.getX() == -this.velocity.getX() && newv.getX() != 0) {
                    this.velocity = new Velocity(this.velocity.getX(), -this.velocity.getY());
                } else if (newv.getY() == -this.velocity.getY() && newv.getY() != 0) {
                    this.velocity = new Velocity(-this.velocity.getX(), this.velocity.getY());
                }
            } else {
                this.velocity = newv;
            }
        }
    }

    /**
     * Moves the ball one step according to its current velocity and checks for collisions.
     */
    public void moveOneStep() {
        CollisionInfo c1 = null, c2 = null, c3 = null;
        Line l1 = new Line(this.center.getX(), this.center.getY(), this.center.getX()
                + this.velocity.getX() + this.raduis, this.center.getY() + this.velocity.getY() + this.raduis);
        Line l2 = new Line(this.center.getX(), this.center.getY(), this.center.getX()
                + this.velocity.getX() - this.raduis, this.center.getY() + this.velocity.getY() - this.raduis);
        // check for collision in the lines
        c1 = game.getClosestCollision(l1);
        c2 = game.getClosestCollision(l2);
        if (c1 != null) {
            Collidable collidable = c1.collisionObject();
            Velocity newv = collidable.hit(this, c1.collisionPoint(), this.velocity);
            checkTwoBorders(((CollisionInfo) c1).collisionPoint(), newv, c1.collisionObject());

        } else if (c2 != null) {
            Collidable collidable = c2.collisionObject();
            Velocity newv = collidable.hit(this, c2.collisionPoint(), this.velocity);
            checkTwoBorders(c2.collisionPoint(), newv, c2.collisionObject());

        }

        this.center = this.getVelocity().applyToPoint(this.center);


    }

    /**
     * Moves the ball one step and updates the game accordingly.
     */

    @Override
    public void timePassed() {
        this.moveOneStep();

    }

    /**
     * Adds the ball to the given game.
     *
     * @param g the game to add the ball to.
     */

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}
