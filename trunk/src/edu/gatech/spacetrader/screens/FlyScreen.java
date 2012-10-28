/*
 * Comment
 */
package edu.gatech.spacetrader.screens;

import java.awt.Graphics;
import java.awt.Point;

import edu.gatech.spacetrader.main.GamePanel;
import edu.gatech.spacetrader.main.SpaceTrader;
import edu.gatech.spacetrader.planet.Galaxy;
import edu.gatech.spacetrader.planet.Planet;

/**
 */
public class FlyScreen extends Screen {

    /**
     * Field gameScreen.
     */
    private GameScreen gameScreen;

    /**
     * Field panel.
     */
    private GamePanel panel;

    /**
     * Field width.
     */
    /**
     * Field height.
     */
    private int width, height;

    /**
     * Constructor for FlyScreen.
     * 
     * @param gameScreen
     *            GameScreen
     * @param panel
     *            GamePanel
     * @param width
     *            int
     * @param height
     *            int
     */
    public FlyScreen(GameScreen gameScreen, GamePanel panel, int width,
            int height) {
        this.gameScreen = gameScreen;
        this.panel = panel;
        this.width = width;
        this.height = height;
    }

    /**
     * Method draw.
     * 
     * @param g
     *            Graphics
     */
    @Override
    public void draw(Graphics g) {
        gameScreen.getGalaxy().draw(g, panel, width, height);
        g.fillOval((150 * 6) - 5, (100 * 5) - 5, 10, 10);
        g.drawOval(
                ((gameScreen.getCurrentPlanet().getX() * 6) + 10)
                        - (gameScreen.getPlayer().getSpaceCraft().getSpeed() * 10),
                ((gameScreen.getCurrentPlanet().getY() * 5) + 10)
                        - (gameScreen.getPlayer().getSpaceCraft().getSpeed() * 10),
                gameScreen.getPlayer().getSpaceCraft().getSpeed() * 20,
                gameScreen.getPlayer().getSpaceCraft().getSpeed() * 20);
    }

    /**
     * Method checkForClick.
     * 
     * @param point
     *            Point
     */
    @Override
    public void checkForClick(Point point) {
        for (Planet p : gameScreen.getGalaxy().getPlanets()) {
            if (p.checkForClick(point)) {
                System.out.println("Hello!");
            }
        }
    }

    /**
     * Method toString.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "Fly screen";
    }
}
