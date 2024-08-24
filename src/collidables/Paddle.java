// 211604343 Shaked Gitta
package collidables;


import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

import games.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import sprites.Ball;
import sprites.Sprite;
import sprites.Velocity;

/**
 * The Paddle class represents a rectangular paddle that can be moved left or right using the keyboard.
 * It implements the Sprite and Collidable interfaces.
 */


public class Paddle implements Sprite, Collidable {
    private static final int GUI_SIZE_X = 800;
    private biuoop.KeyboardSensor keyboard;
    private geometry.Rectangle rect;
    private Color color;
    private Velocity velocity;
    private double step;

    /**
     * Constructor.
     *
     * @param rect     the rectangle representing the paddle.
     * @param color    the color of the paddle.
     * @param keyboard the keyboard sensor.
     * @param velocity the velocity of the paddle.
     */

    public Paddle(geometry.Rectangle rect, Color color, KeyboardSensor keyboard, Velocity velocity) {
        this.rect = rect;
        this.color = color;
        this.keyboard = keyboard;
        this.velocity = velocity;
        this.step = velocity.getX();

    }

    /**
     * Moves the paddle to the left.
     */


    public void moveLeft() {

        this.velocity.setX(-step);
    }

    /**
     * Moves the paddle to the right.
     */

    public void moveRight() {
        this.velocity.setX(step);

    }

    /**
     * Implements the timePassed method of the Block.Sprite interface.
     * Changes the velocity of the paddle according to the keyboard input,
     * and updates the position of the paddle accordingly.
     */

    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();

        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
        if (this.rect.getUpperLeft().getX() + this.velocity.getX() <= 0) {
            this.velocity.setX(0);

        } else if (this.rect.getUpperLeft().getX() + this.velocity.getX() + this.rect.getWidth() >= GUI_SIZE_X) {
            this.velocity.setX(0);

        }

        Point point = this.velocity.applyToPoint(this.rect.getUpperLeft());
        this.rect.setUpperLeft(point);
        this.velocity.setX(0);


    }

    /**
     * Implements the drawOn method of the Sprite interface.
     * Draws the paddle on the given DrawSurface.
     *
     * @param d the DrawSurface to draw on.
     */
    @Override

    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
    }

    /**
     * Implements the getCollisionRectangle method of the Collidable interface.
     *
     * @return the rectangle representing the paddle.
     */

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * Notifies the Block that it was hit by a collidable object at a given collision point with a given velocity.
     * The method returns the new velocity expected after the hit based on the force the object inflicted on the Block.
     *
     * @param hitter          the ball that hit the block
     * @param collisionPoint  the point where the collision occurred
     * @param currentVelocity the current velocity of the object that hit the block
     * @return the new velocity expected after the hit
     */
    @Override

    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // Create lines representing the four edges of the rectangle
        Line top = new Line(rect.getUpperLeft().getX(), rect.getUpperLeft().getY(), rect.getUpperLeft().getX()
                + rect.getWidth(), rect.getUpperLeft().getY());
        Line right = new Line(rect.getUpperLeft().getX() + rect.getWidth(), rect.getUpperLeft().getY(),
                rect.getUpperLeft().getX() + rect.getWidth(), rect.getUpperLeft().getY() + rect.getHeight());
        Line bottom = new Line(rect.getUpperLeft().getX(), rect.getUpperLeft().getY() + rect.getHeight(),
                rect.getUpperLeft().getX() + rect.getWidth(), rect.getUpperLeft().getY() + rect.getHeight());
        Line left = new Line(rect.getUpperLeft().getX(), rect.getUpperLeft().getY(), rect.getUpperLeft().getX(),
                rect.getUpperLeft().getY() + rect.getHeight());

        // Handle collision with the top edge of the rectangle
        if (top.intersectionWithPoint(collisionPoint)) {
            double parts = this.rect.getWidth() / 5;
            double startp = this.rect.getUpperLeft().getX();
            double cpointX = collisionPoint.getX();
            double part1 = this.rect.getUpperLeft().getX() + parts;
            double part2 = this.rect.getUpperLeft().getX() + 2 * parts;
            double part3 = this.rect.getUpperLeft().getX() + 3 * parts;
            double part4 = this.rect.getUpperLeft().getX() + 4 * parts;
            double part5 = this.rect.getUpperLeft().getX() + 5 * parts;
            Velocity velocity1 = new Velocity();
            // Calculate the speed of the ball using its current velocity
            double speed = Math.sqrt((currentVelocity.getX() * currentVelocity.getX())
                    + (currentVelocity.getY() * currentVelocity.getY()));
            // Calculate the new velocity based on the position of the collision point


            if (cpointX >= startp && cpointX <= part1) {
                velocity1 = currentVelocity.fromAngleAndSpeed(300, speed);
                velocity1.setY(-velocity1.getY());

            } else if (cpointX >= part1 && cpointX <= part2) {
                velocity1 = currentVelocity.fromAngleAndSpeed(330, speed);
                velocity1.setY(-velocity1.getY());


            } else if (cpointX >= part2 && cpointX <= part3) {
                velocity1 = new Velocity(currentVelocity.getX(), -currentVelocity.getY());

            } else if (cpointX >= part3 && cpointX <= part4) {
                velocity1 = currentVelocity.fromAngleAndSpeed(30, speed);
                velocity1.setY(-velocity1.getY());

            } else if (cpointX >= part4 && cpointX <= part5) {
                velocity1 = currentVelocity.fromAngleAndSpeed(60, speed);
                velocity1.setY(-velocity1.getY());


            }
//            System.out.println(velocity1.getX());
            currentVelocity = velocity1;

            // Handle collision with the left or right or bottom edge of the rectangle

        } else if (bottom.intersectionWithPoint(collisionPoint)) {
            currentVelocity.setY((-1) * currentVelocity.getY());

        }
        if (left.intersectionWithPoint(collisionPoint) || right.intersectionWithPoint(collisionPoint)) {
            currentVelocity.setX((-1) * currentVelocity.getX());

        }
        // Return the updated velocity
        return currentVelocity;

    }


    /**
     * Adds the Paddle to a given games.Game object.
     *
     * @param g the games.Game to add the block to
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

}