// 211604343 Shaked Gitta

package sprites;


//package games.Animations;

import biuoop.DrawSurface;
import games.GameLevel;
//import biuoop.Sprite;

import java.awt.Color;
/**
 * The BuildingBackground class represents a building background sprite for a game.
 * It implements the Sprite interface.
 */
public class BuildingBackground implements Sprite {
    private int buildingX;
    private int buildingY;
    private int buildingWidth;
    private int buildingHeight;
    /**
     * Constructs a BuildingBackground instance with default parameters.
     */
    public BuildingBackground() {
        this.buildingX = 600;
        this.buildingY = 400;
        this.buildingWidth = 50;
        this.buildingHeight = 200;
    }

    @Override
    public void drawOn(DrawSurface d) {
        // Draw the building background
        drawBuildingBackground(d);
    }

    @Override
    public void timePassed() {
        // Add logic if needed for time-based behavior
    }

    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    // Helper method to draw the building background
    private void drawBuildingBackground(DrawSurface d) {
        // Set the background color to light gray
        d.setColor(Color.LIGHT_GRAY);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

        // Draw the building
        drawBuilding(d);
    }

    // Helper method to draw a single building
    private void drawBuilding(DrawSurface d) {
        // Set the building color to dark gray
        d.setColor(Color.DARK_GRAY);
        d.fillRectangle(buildingX, buildingY, buildingWidth, buildingHeight);

        // Draw windows on the building
        int windowSize = 20;
        int windowGap = 10;
        int numWindows = buildingHeight / (windowSize + windowGap);
        int windowY = buildingY + windowGap;
        int windowX = buildingX + windowGap;

        // Draw multiple rows of windows
        for (int i = 0; i < numWindows; i++) {
            // Draw a row of windows
            for (int j = 0; j < buildingWidth / (windowSize + windowGap); j++) {
                d.setColor(Color.WHITE);
                d.fillRectangle(windowX, windowY, windowSize, windowSize);
                windowX += windowSize + windowGap;
            }

            // Move to the next row
            windowX = buildingX + windowGap;
            windowY += windowSize + windowGap;
        }
    }
}
