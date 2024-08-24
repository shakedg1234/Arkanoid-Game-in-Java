// 211604343 Shaked Gitta
package sprites;
import biuoop.DrawSurface;

import games.GameLevel;



import java.awt.Color;
/**
 * The SunBackground class represents a sun with clouds background sprite for a game.
 * It implements the Sprite interface.
 */

public class SunBackground implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        // Draw the sun background
        drawSunBackground(d);

        // Draw clouds
        drawClouds(d);
    }

    @Override
    public void timePassed() {
        // Add logic if needed for time-based behavior
    }

    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    // Helper method to draw the sun background
    private void drawSunBackground(DrawSurface d) {
        // Set the background color to sky blue
        d.setColor(new Color(135, 206, 235));
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

        // Draw the sun
        int sunRadius = 60;
        int sunCenterX = 100;
        int sunCenterY = 100;

        // Draw the sun rays
        d.setColor(Color.YELLOW);
        for (int i = 0; i < 360; i += 30) {
            double angle = Math.toRadians(i);
            int rayEndX = (int) (sunCenterX + sunRadius * Math.cos(angle));
            int rayEndY = (int) (sunCenterY + sunRadius * Math.sin(angle));
            d.drawLine(sunCenterX, sunCenterY, rayEndX, rayEndY);
        }

        // Draw the sun's center
        d.setColor(Color.YELLOW);
        d.fillCircle(sunCenterX, sunCenterY, sunRadius / 4);
    }

    // Helper method to draw clouds
    private void drawClouds(DrawSurface d) {
        int cloudWidth = 80;
        int cloudHeight = 40;
        int cloud1X = 150;
        int cloud1Y = 120;
        int cloud2X = 300;
        int cloud2Y = 200;

        // Draw cloud 1
        d.setColor(Color.WHITE);
        d.fillOval(cloud1X, cloud1Y, cloudWidth, cloudHeight);
        d.fillOval(cloud1X + cloudWidth / 2, cloud1Y - cloudHeight / 2, cloudWidth, cloudHeight);
        d.fillOval(cloud1X + cloudWidth, cloud1Y, cloudWidth, cloudHeight);

        // Draw cloud 2
        d.fillOval(cloud2X, cloud2Y, cloudWidth, cloudHeight);
        d.fillOval(cloud2X + cloudWidth / 2, cloud2Y - cloudHeight / 2, cloudWidth, cloudHeight);
        d.fillOval(cloud2X + cloudWidth, cloud2Y, cloudWidth, cloudHeight);
    }
}
